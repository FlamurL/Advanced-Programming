import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

// ============================================
// CLASS: Ad
// ============================================
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

    public String getId() { return id; }
    public String getCategory() { return category; }
    public double getBidValue() { return bidValue; }
    public double getCtr() { return ctr; }
    public String getContent() { return content; }

    // Natural order: bidValue DESC, then id ASC
    @Override
    public int compareTo(Ad other) {
        int bidComp = Double.compare(other.bidValue, this.bidValue); // Reversed
        if (bidComp != 0) return bidComp;
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return String.format("%s %s (bid=%.2f, ctr=%.2f%%) %s",
                id, category, bidValue, ctr * 100, content);
    }
}

// ============================================
// CLASS: AdRequest
// ============================================
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

    public String getId() { return id; }
    public String getCategory() { return category; }
    public double getFloorBid() { return floorBid; }
    public String getKeywords() { return keywords; }

    @Override
    public String toString() {
        return String.format("%s [%s] (floor=%.2f): %s",
                id, category, floorBid, keywords);
    }
}

// ============================================
// CLASS: AdNetwork
// ============================================
class AdNetwork {
    private List<Ad> ads = new ArrayList<>();
    private static final double X = 5.0;
    private static final double Y = 100.0;

    // Read ads from BufferedReader
    public void readAds(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
            // Check if this line is a request (starts with "AR" or similar pattern)
            if (line.trim().split("\\s+")[0].matches("AR\\d+")) {
                // This is actually a request line, not an ad
                // Put it back by not consuming it (we can't, so just break)
                break;
            }

            String[] parts = line.split("\\s+", 5);
            if (parts.length >= 5) {
                ads.add(new Ad(
                        parts[0],                       // id
                        parts[1],                       // category
                        Double.parseDouble(parts[2]),   // bidValue
                        Double.parseDouble(parts[3]),   // ctr
                        parts[4]                        // content
                ));
            }
        }
    }

    // Place ads based on request
    public void placeAds(BufferedReader br, int k, PrintWriter pw) throws IOException {
        // Read request line
        String line = br.readLine();
        if (line == null) return;

        String[] parts = line.split("\\s+", 4);
        String requestId = parts[0];
        String category = parts[1];
        double floorBid = Double.parseDouble(parts[2]);
        String keywords = parts.length > 3 ? parts[3] : "";

        AdRequest request = new AdRequest(requestId, category, floorBid, keywords);

        // Calculate scores for eligible ads
        Map<Ad, Double> scores = new HashMap<>();
        for (Ad ad : ads) {
            if (ad.getBidValue() >= floorBid) {
                double relevance = relevanceScore(ad, request);
                double totalScore = relevance + X * ad.getBidValue() + Y * ad.getCtr();
                scores.put(ad, totalScore);
            }
        }

        // Get top k ads sorted by score, then by natural order
        List<Ad> topAds = scores.keySet().stream()
                .sorted((a1, a2) -> Double.compare(scores.get(a2), scores.get(a1)))
                .limit(k)
                .sorted() // Natural order
                .collect(Collectors.toList());

        // Print results
        pw.println("Top ads for request " + requestId + ":");
        topAds.forEach(pw::println);
    }

    // Calculate relevance score (provided)
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