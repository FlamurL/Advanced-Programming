import java.io.*;
import java.util.*;

class Ad implements Comparable<Ad> {
    private String id;
    private String category;
    private double bidValue;
    private double ctr;
    private String content;

    public Ad(String id, String category, double bidValue, double ctr, String content) {
        this.id = id;
        this.category = category;
        this.bidValue = bidValue;
        this.ctr = ctr;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getBidValue() {
        return bidValue;
    }

    public double getCtr() {
        return ctr;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("%s %s (bid=%.2f, ctr=%.2f%%) %s",
                id, category, bidValue, ctr * 100, content);
    }

    @Override
    public int compareTo(Ad other) {
        // Опаѓачки редослед по bidValue
        int bidCompare = Double.compare(other.bidValue, this.bidValue);
        if (bidCompare != 0) {
            return bidCompare;
        }
        // Растечки редослед по id
        return this.id.compareTo(other.id);
    }
}

class AdRequest {
    private String id;
    private String category;
    private double floorBid;
    private String keywords;

    public AdRequest(String id, String category, double floorBid, String keywords) {
        this.id = id;
        this.category = category;
        this.floorBid = floorBid;
        this.keywords = keywords;
    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public double getFloorBid() {
        return floorBid;
    }

    public String getKeywords() {
        return keywords;
    }

    @Override
    public String toString() {
        return String.format("%s [%s] (floor=%.2f): %s",
                id, category, floorBid, keywords);
    }
}

class AdNetwork {
    private ArrayList<Ad> ads;
    private BufferedReader sharedReader;

    public AdNetwork() {
        this.ads = new ArrayList<>();
    }

    // Метод за InputStream (според спецификација)
    public void readAds(InputStream in) throws IOException {
        sharedReader = new BufferedReader(new InputStreamReader(in));
        readAdsInternal();
    }

    // Преоптоварен метод за BufferedReader (за main)
    public void readAds(BufferedReader br) throws IOException {
        sharedReader = br;
        readAdsInternal();
    }

    private void readAdsInternal() throws IOException {
        String line;

        while ((line = sharedReader.readLine()) != null && !line.trim().isEmpty()) {
            String[] parts = line.split("\\s+");

            // Проверка дали е реклама (има 4+ делови и 4-тиот е број помал од 1)
            if (parts.length >= 5) {
                try {
                    double bidValue = Double.parseDouble(parts[2]);
                    double ctr = Double.parseDouble(parts[3]);

                    // Ако четвртиот е помал од 1, тоа е CTR (реклама)
                    if (ctr < 1.0) {
                        String id = parts[0];
                        String category = parts[1];

                        // Содржината е остатокот од линијата
                        StringBuilder content = new StringBuilder();
                        for (int i = 4; i < parts.length; i++) {
                            if (i > 4) content.append(" ");
                            content.append(parts[i]);
                        }

                        ads.add(new Ad(id, category, bidValue, ctr, content.toString()));
                    } else {
                        // Не е реклама, веројатно е барање - врати се
                        break;
                    }
                } catch (NumberFormatException e) {
                    // Не е валиден формат, прескокни или врати се
                    break;
                }
            } else {
                break;
            }
        }
    }

    // Метод за InputStream (според спецификација)
    public List<Ad> placeAds(InputStream inputStream, int k, OutputStream outputStream) throws IOException {
        // Ако sharedReader не е сетиран, креирај нов
        if (sharedReader == null) {
            sharedReader = new BufferedReader(new InputStreamReader(inputStream));
        }
        PrintWriter pw = new PrintWriter(outputStream);
        return placeAdsInternal(k, pw);
    }

    // Преоптоварен метод за BufferedReader (за main)
    public List<Ad> placeAds(BufferedReader br, int k, OutputStream outputStream) throws IOException {
        PrintWriter pw = new PrintWriter(outputStream);
        return placeAdsInternal(k, pw);
    }

    // Преоптоварен метод за BufferedReader и PrintWriter (за main)
    public List<Ad> placeAds(BufferedReader br, int k, PrintWriter pw) throws IOException {
        return placeAdsInternal(k, pw);
    }

    private List<Ad> placeAdsInternal(int k, PrintWriter pw) throws IOException {
        String line = sharedReader.readLine();
        if (line == null || line.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String[] parts = line.split("\\s+");
        String id = parts[0];
        String category = parts[1];
        double floorBid = Double.parseDouble(parts[2]);

        // Клучните зборови се остатокот
        StringBuilder keywords = new StringBuilder();
        for (int i = 3; i < parts.length; i++) {
            if (i > 3) keywords.append(" ");
            keywords.append(parts[i]);
        }

        AdRequest request = new AdRequest(id, category, floorBid, keywords.toString());

        // Филтрирај реклами со bidValue >= floorBid
        List<Ad> eligibleAds = new ArrayList<>();
        for (Ad ad : ads) {
            if (ad.getBidValue() >= floorBid) {
                eligibleAds.add(ad);
            }
        }

        // Пресметај totalScore за секоја реклама
        final double x = 5.0;
        final double y = 100.0;

        Map<Ad, Double> scores = new HashMap<>();
        for (Ad ad : eligibleAds) {
            int relevance = relevanceScore(ad, request);
            double totalScore = relevance + x * ad.getBidValue() + y * ad.getCtr();
            scores.put(ad, totalScore);
        }

        // Сортирај по totalScore во опаѓачки редослед
        eligibleAds.sort((a1, a2) -> Double.compare(scores.get(a2), scores.get(a1)));

        // Земи топ k реклами
        List<Ad> topAds = new ArrayList<>();
        for (int i = 0; i < Math.min(k, eligibleAds.size()); i++) {
            topAds.add(eligibleAds.get(i));
        }

        // Сортирај според природниот редослед (Comparable)
        Collections.sort(topAds);

        // Печати резултати
        pw.println("Top ads for request " + request.getId() + ":");
        for (Ad ad : topAds) {
            pw.println(ad);
        }

        return topAds;
    }

    private int relevanceScore(Ad ad, AdRequest req) {
        int score = 0;
        if (ad.getCategory().equalsIgnoreCase(req.getCategory())) score += 10;
        String[] adWords = ad.getContent().toLowerCase().split("\\s+");
        String[] keywords = req.getKeywords().toLowerCase().split("\\s+");
        for (String kw : keywords) {
            for (String aw : adWords) {
                if (kw.equals(aw)) score++;
            }
        }
        return score;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        AdNetwork network = new AdNetwork();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int k = Integer.parseInt(br.readLine().trim());
        if (k == 0) {
            network.readAds(br);
            network.placeAds(br, 1, pw);
        } else if (k == 1) {
            network.readAds(br);
            network.placeAds(br, 3, pw);
        } else {
            network.readAds(br);
            network.placeAds(br, 8, pw);
        }
        pw.flush();
    }
}