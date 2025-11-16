import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SubjectWithGrade {
    private String subject;
    private int grade;

    public SubjectWithGrade(String subject, int grade) {
        this.subject = subject;
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public int getGrade() {
        return grade;
    }
}

class Applicant {
    private int id;
    private String name;
    private double gpa;
    private List<SubjectWithGrade> subjectsWithGrade;
    private StudyProgramme studyProgramme;

    public Applicant(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.subjectsWithGrade = new ArrayList<>();
    }

    public void addSubjectAndGrade(String subject, int grade) {
        subjectsWithGrade.add(new SubjectWithGrade(subject, grade));
    }

    public double calculatePoints() {
        double points = gpa * 12;

        List<String> appropriateSubjects = studyProgramme.getFaculty().getAppropriateSubjects();

        for (SubjectWithGrade swg : subjectsWithGrade) {
            if (appropriateSubjects.contains(swg.getSubject())) {
                points += swg.getGrade() * 2;
            } else {
                points += swg.getGrade() * 1.2;
            }
        }

        return points;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public StudyProgramme getStudyProgramme() {
        return studyProgramme;
    }

    public void setStudyProgramme(StudyProgramme studyProgramme) {
        this.studyProgramme = studyProgramme;
    }

    public List<SubjectWithGrade> getSubjectsWithGrade() {
        return subjectsWithGrade;
    }

    public long countAppropriateSubjects() {
        List<String> appropriateSubjects = studyProgramme.getFaculty().getAppropriateSubjects();
        return subjectsWithGrade.stream()
                .filter(swg -> appropriateSubjects.contains(swg.getSubject()))
                .count();
    }
}

class StudyProgramme {
    private String code;
    private String name;
    private Faculty faculty;
    private int numPublicQuota;
    private int numPrivateQuota;
    private int enrolledInPublicQuota;
    private int enrolledInPrivateQuota;
    private List<Applicant> applicants;

    public StudyProgramme(String code, String name, Faculty faculty, int numPublicQuota, int numPrivateQuota) {
        this.code = code;
        this.name = name;
        this.faculty = faculty;
        this.numPublicQuota = numPublicQuota;
        this.numPrivateQuota = numPrivateQuota;
        this.enrolledInPublicQuota = 0;
        this.enrolledInPrivateQuota = 0;
        this.applicants = new ArrayList<>();
    }

    public void addApplicant(Applicant applicant) {
        applicants.add(applicant);
    }

    public void calculateEnrollmentNumbers() {
        List<Applicant> sortedApplicants = applicants.stream()
                .sorted(Comparator.compare(Applicant::calculatePoints).reversed())
                .collect(Collectors.toList());

        enrolledInPublicQuota = 0;
        enrolledInPrivateQuota = 0;

        for (int i = 0; i < sortedApplicants.size(); i++) {
            if (enrolledInPublicQuota < numPublicQuota) {
                enrolledInPublicQuota++;
            } else if (enrolledInPrivateQuota < numPrivateQuota) {
                enrolledInPrivateQuota++;
            }
        }
    }

    public double getAcceptanceRate() {
        int totalQuota = numPublicQuota + numPrivateQuota;
        int totalEnrolled = enrolledInPublicQuota + enrolledInPrivateQuota;
        return ((double) totalEnrolled / totalQuota) * 100;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");

        List<Applicant> sortedApplicants = applicants.stream()
                .sorted(Comparator.comparingDouble(Applicant::calculatePoints).reversed())
                .collect(Collectors.toList());

        sb.append("Public Quota:\n");
        int publicCount = 0;
        for (Applicant app : sortedApplicants) {
            if (publicCount < enrolledInPublicQuota) {
                sb.append(String.format("Id: %d, Name: %s, GPA: %.1f - %s\n",
                        app.getId(), app.getName(), app.getGpa(), app.calculatePoints()));
                publicCount++;
            }
        }

        sb.append("Private Quota:\n");
        int privateCount = 0;
        for (Applicant app : sortedApplicants) {
            if (publicCount > 0) {
                publicCount--;
                continue;
            }
            if (privateCount < enrolledInPrivateQuota) {
                sb.append(String.format("Id: %d, Name: %s, GPA: %.1f - %s\n",
                        app.getId(), app.getName(), app.getGpa(), app.calculatePoints()));
                privateCount++;
            }
        }

        sb.append("Rejected:\n");
        int totalEnrolled = enrolledInPublicQuota + enrolledInPrivateQuota;
        for (int i = totalEnrolled; i < sortedApplicants.size(); i++) {
            Applicant app = sortedApplicants.get(i);
            sb.append(String.format("Id: %d, Name: %s, GPA: %.1f - %s\n",
                    app.getId(), app.getName(), app.getGpa(), app.calculatePoints()));
        }
        sb.append("\n");

        return sb.toString();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public List<Applicant> getApplicants() {
        return applicants;
    }

    public int getEnrolledInPublicQuota() {
        return enrolledInPublicQuota;
    }

    public int getEnrolledInPrivateQuota() {
        return enrolledInPrivateQuota;
    }
}

class Faculty {
    private String shortName;
    private List<String> appropriateSubjects;
    private List<StudyProgramme> studyProgrammes;

    public Faculty(String shortName) {
        this.shortName = shortName;
        this.appropriateSubjects = new ArrayList<>();
        this.studyProgrammes = new ArrayList<>();
    }

    public void addSubject(String subject) {
        appropriateSubjects.add(subject);
    }

    public void addStudyProgramme(StudyProgramme studyProgramme) {
        studyProgrammes.add(studyProgramme);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Faculty: ").append(shortName).append("\n");
        sb.append("Subjects: ").append(appropriateSubjects).append("\n");
        sb.append("Study Programmes:\n");

        List<StudyProgramme> sortedProgrammes = studyProgrammes.stream()
                .sorted(Comparator
                        .comparingLong((StudyProgramme sp) ->
                                sp.getApplicants().stream()
                                        .mapToLong(Applicant::countAppropriateSubjects)
                                        .min()
                                        .orElse(Long.MAX_VALUE))
                        .thenComparing(Comparator.comparingDouble(StudyProgramme::getAcceptanceRate).reversed())
                        .thenComparing((sp1, sp2) -> {
                            double maxPoints1 = sp1.getApplicants().stream()
                                    .mapToDouble(Applicant::calculatePoints)
                                    .max()
                                    .orElse(0);
                            double maxPoints2 = sp2.getApplicants().stream()
                                    .mapToDouble(Applicant::calculatePoints)
                                    .max()
                                    .orElse(0);
                            return Double.compare(maxPoints2, maxPoints1);
                        }))
                .collect(Collectors.toList());

        for (StudyProgramme sp : sortedProgrammes) {
            sb.append(sp.toString());
        }

        return sb.toString();
    }

    public String getShortName() {
        return shortName;
    }

    public List<String> getAppropriateSubjects() {
        return appropriateSubjects;
    }

    public List<StudyProgramme> getStudyProgrammes() {
        return studyProgrammes;
    }
}

class EnrollmentsIO {
    public static void printRanked(List<Faculty> faculties) {
        faculties.forEach(faculty -> System.out.print(faculty.toString()));
    }

    public static void readEnrollments(List<StudyProgramme> studyProgrammes, InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        br.lines().forEach(line -> {
            String[] parts = line.split(";");
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            double gpa = Double.parseDouble(parts[2]);

            Applicant applicant = new Applicant(id, name, gpa);

            // Предметите и оценките се наизменично: subject1, grade1, subject2, grade2, ...
            for (int i = 3; i < parts.length - 1; i += 2) {
                String subject = parts[i];
                int grade = Integer.parseInt(parts[i + 1]);
                applicant.addSubjectAndGrade(subject, grade);
            }

            String programmeCode = parts[parts.length - 1];
            StudyProgramme studyProgramme = studyProgrammes.stream()
                    .filter(sp -> sp.getCode().equals(programmeCode))
                    .findFirst()
                    .orElse(null);

            if (studyProgramme != null) {
                applicant.setStudyProgramme(studyProgramme);
                studyProgramme.addApplicant(applicant);
            }
        });
    }
}

public class EnrollmentsTest {
    public static void main(String[] args) {
        Faculty finki = new Faculty("FINKI");
        finki.addSubject("Mother Tongue");
        finki.addSubject("Mathematics");
        finki.addSubject("Informatics");

        Faculty feit = new Faculty("FEIT");
        feit.addSubject("Mother Tongue");
        feit.addSubject("Mathematics");
        feit.addSubject("Physics");
        feit.addSubject("Electronics");

        Faculty medFak = new Faculty("MEDFAK");
        medFak.addSubject("Mother Tongue");
        medFak.addSubject("English");
        medFak.addSubject("Mathematics");
        medFak.addSubject("Biology");
        medFak.addSubject("Chemistry");

        StudyProgramme si = new StudyProgramme("SI", "Software Engineering", finki, 4, 4);
        StudyProgramme it = new StudyProgramme("IT", "Information Technology", finki, 2, 2);
        finki.addStudyProgramme(si);
        finki.addStudyProgramme(it);

        StudyProgramme kti = new StudyProgramme("KTI", "Computer Technologies and Engineering", feit, 3, 3);
        StudyProgramme ees = new StudyProgramme("EES", "Electro-energetic Systems", feit, 2, 2);
        feit.addStudyProgramme(kti);
        feit.addStudyProgramme(ees);

        StudyProgramme om = new StudyProgramme("OM", "General Medicine", medFak, 6, 6);
        StudyProgramme nurs = new StudyProgramme("NURS", "Nursing", medFak, 2, 2);
        medFak.addStudyProgramme(om);
        medFak.addStudyProgramme(nurs);

        List<StudyProgramme> allProgrammes = new ArrayList<>();
        allProgrammes.add(si);
        allProgrammes.add(it);
        allProgrammes.add(kti);
        allProgrammes.add(ees);
        allProgrammes.add(om);
        allProgrammes.add(nurs);

        EnrollmentsIO.readEnrollments(allProgrammes, System.in);

        List<Faculty> allFaculties = new ArrayList<>();
        allFaculties.add(finki);
        allFaculties.add(feit);
        allFaculties.add(medFak);

        allProgrammes.stream().forEach(StudyProgramme::calculateEnrollmentNumbers);

        EnrollmentsIO.printRanked(allFaculties);
    }
}