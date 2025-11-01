/*
 Дадени ви се следниве класи:

Класа Doctor

Преставува еден доктор со основните информации за него: бројот на лиценцата, неговото име, ниво на експертиза (1-10), број на пациенти
Доколку нивото на експертиза е 10, се смета дека докторот е Chief.
Имплементиран е toString методот кој го печати докторот во читлив формат (име, број на лиценца, специјализација, број на пациенти и доколку е со највисоко ниво на експертиза се печати и [Chief])
При промена на нивото на експертиза, вредноста мора да се движи во рамките од 1-10 и не смее да биде помала од претходната
Класа EmergencyRoom

репрезентира еден ургентен центар во една болница и содржи информации за: името на болницата, медицински персонал (низа од објекти Doctor), капацитет
Имплементирани се следниве методи: treat, forEach, count, findFirst, filter, mapToLabels, mutate, conditionalMutate, countForEvaluation, evaluate
treat(Supplier<Doctor> supplier) - додава доктор во ургентниот центар, доколку има слободно место
forEach(Consumer<Doctor> action) - применува зададена акција (Consumer) врз секој доктор во низата (пример: печатење)
count(Predicate<Doctor> condition) - го враќа бројот на доктори кои го исполнуваат дадениот услов
findFirst(Predicate<Doctor> condition) - го враќа првиот доктор кој исполнува даден услов
filter(Predicate<Student> condition - Враќа нова низа која ги содржи само докторите кои го исполнуваат условот.
mapToLabels(Function<Student, String> mapper) - Враќа низа од текстуални описи, добиени со трансформирање на секој доктор со дадената функција.
mutate(Consumer<Student> mutator) - Применува промена на сите доктори (на пример, зголемување на нивото на експертиза).
conditionalMutate(Predicate<Student> condition, Consumer<Student> mutator) - Ја применува промената само на докторите кои го исполнуваат дадениот услов.
countForEvaluation(DoctorEvaluator evaluator) - Користи DoctorEvaluator за да изброи колку доктори исполнуваат еден услов
evaluate(DoctorEvaluator evaluator) - Враќа нова низа која ги содржи сите доктори кои исполнуваат услов поставен со DoctorEvaluator
toString() - Враќа текстуален опис на ургентниот центар, кој ги содржи името на болницата, бројот на доктори кои моментално работат во него и списокот од истите.
Од ваша страна потребно е да:

Креирате функциски интерфејс DoctorEvaluator кој ќе има еден метод: boolean evaluate(Doctor doctor);
Да креирате класа HighExpertiseEvaluator кој ќе враќа TRUE само доколку докторот има ниво на експертиза поголем или еднаков на 7.
Да ги разрешите барањата во main делот:
Отворете Scanner и прочитајте цел број n што го означува бројот на доктори кои ќе се внесат.
Креирајте Supplier<Student> кој чита податоци за еден доктор од конзолата (број на лиценца, име, ниво на експертиза и број на пациенти) и враќа нов објект Doctor.
Додадете n доктори во користејќи го методот treat.
Користете Consumer<Student> заедно со forEach за да ги испечатите сите доктори кои работат моментално во ургентниот центар.
Искористете ги креираните функциски интерфејси за да одредите кои доктори:
имаат повеќе од 20 пациенти
имаат повисоко ниво на експертиза (7+)
Комбинирајте ги двете состојби од функциските интерфејси и искористете го методот evaluate од класата EmergencyRoom за да ги прикажеш само тие доктори.
Користете findFirst за да го пронајдите и прикажете Chief докторот во ургентниот центар.
Користете mutate за да и го зголемите нивото на експертиза на сите доктори за 1.
Користете conditionalMutate за да ја зголемите експертизата за 1 само на докторите со повеќе од 30 пациенти.
Користете mapToLabels за да ги трансформирате сите доктори во текстуални описи и испечати ги.
На крај, испечатете ги сите информации за ургентниот центар со користење на методот toString.

---

You are given the following classes:

Class Doctor

Represents a doctor with basic information about him: license number, his name, expertise level (1-10), number of patients
If the expertise level is 10, the doctor is considered to be a Chief.
The toString method has been implemented, which prints the doctor in a readable format (name, license number, specialization, number of patients, and if he has the highest level of expertise, [Chief] is also printed.)
When changing the level of expertise, the value must be in the range of 1-10 and must not be lower than the previous one
Class EmergencyRoom

represents an emergency room in a hospital and contains information about: the name of the hospital, medical staff (an array of Doctor objects), capacity
The following methods have been implemented: treat, forEach, count, findFirst, filter, mapToLabels, mutate, conditionalMutate, countForEvaluation, evaluate
treat(Supplier<Doctor> supplier) - adds a doctor to the emergency room, if there is a free place
forEach(Consumer<Doctor> action) - applies a given action (Consumer) to each doctor in the array (example: print)
count(Predicate<Doctor> condition) - returns the number of doctors who meet the given condition
findFirst(Predicate<Doctor> condition) - returns the first doctor who meets a given condition
filter(Predicate<Student> condition) - Returns a new array containing only the doctors who meet the condition.
mapToLabels(Function<Student, String> mapper) - Returns an array of text descriptions, obtained by transforming each doctor with the given function.
mutate(Consumer<Student> mutator) - Applies a change to all doctors (for example, increasing the level of expertise)
conditionalMutate(Predicate<Student> condition, Consumer<Student> mutator) - Applies the change only to doctors who meet the given condition.
countForEvaluation(DoctorEvaluator evaluator) - Uses DoctorEvaluator to count how many doctors meet a condition
evaluate(DoctorEvaluator evaluator) - Returns a new array containing all doctors who meet the condition set by DoctorEvaluator
toString() - Returns a text description of the emergency center, containing the name of the hospital, the number of doctors currently working in it and a list of them.
On your part, you need to:

Create a functional interface DoctorEvaluator that will have one method: boolean evaluate(Doctor doctor);
Create a class HighExpertiseEvaluator that will return TRUE only if the doctor has an expertise level greater than or equal to 7.
Resolve the requirements in the main section:
Open a Scanner and read an integer n that indicates the number of doctors to be entered.
Create a Supplier<Student> that reads data about a doctor from the console (license number, name, expertise level and number of patients) and returns a new Doctor object.
Add n doctors to using the treat method.
Use Consumer<Student> together with forEach to print all doctors currently working in the emergency center.
Use the created functional interfaces to determine which doctors:
have more than 20 patients
have a higher level of expertise (7+)
Combine the two states from the functional interfaces and use the evaluate method of the EmergencyRoom class to display only those doctors.
Use findFirst to find and display the Chief doctor in the emergency room.
Use mutate to increase the expertise level of all doctors by 1.
Use conditionalMutate to increase the expertise level of only doctors with more than 30 patients by 1.
Use mapToLabels to transform all doctors into text descriptions and print them.
Finally, print all the information about the emergency room using the toString method.

Input	
5
1001 Alice 5 10
1002 Bob 7 25
1003 Carol 10 40
1004 David 2 15
1005 Eva 8 35

Result
Doctors that are treating:
Alice (1001) 5 10 
Bob (1002) 7 25 
Carol (1003) 10 40 [Chief]
David (1004) 2 15 
Eva (1005) 8 35 

=== All Doctors ===
Alice (1001) 5 10 
Bob (1002) 7 25 
Carol (1003) 10 40 [Chief]
David (1004) 2 15 
Eva (1005) 8 35 

=== Doctors with higher number of patients and a higher level of expertise ===
Bob (1002) 7 25 
Carol (1003) 10 40 [Chief]
Eva (1005) 8 35 

=== Chief doctor (level = 10) ===
Carol (1003) 10 40 [Chief]

=== Increase all expertise levels by 1 (max 10) ===
Alice (1001) 6 10 
Bob (1002) 8 25 
Carol (1003) 10 40 [Chief]
David (1004) 3 15 
Eva (1005) 9 35 

=== Increase the level of expertise of every doctor by 1 ===

=== Map doctors to labels ===
Name: Alice, Level: 6
Name: Bob, Level: 8
Name: Carol, Level: 10
Name: David, Level: 3
Name: Eva, Level: 10



 */




import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;


@FunctionalInterface
interface DoctorEvaluator {
    boolean evaluate(Doctor doctor);
}


class HighExpertiseEvaluator implements DoctorEvaluator {
    @Override
    public boolean evaluate(Doctor doctor) {
        return doctor.getLevel() >= 7;
    }
}

class Doctor {
    private final int licenseNumber;    
    private String name;
    private int level;                
    private int patients;

    public Doctor(int licenseNumber, String name, int level, int patients) {
        this.licenseNumber = licenseNumber;
        this.name = name;
        this.level = level;
        this.patients = patients;
    }

    public int getLicenseNumber() {
        return licenseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level > 10) {
            this.level = 10;
            return;
        }
        if (level < 1) {
            this.level = 1;
            return;
        }
        this.level = level;
    }

    public int getPatients() {
        return patients;
    }

    public void setPatients(int patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return String.format("%s (%d) %d %d %s", name, licenseNumber, level, patients, level == 10 ? "[Chief]" : "");
    }
}

class EmergencyRoom {
    private final String hospitalName;
    private final Doctor[] doctors;
    private int size = 0;

    public EmergencyRoom(String title, int doctorCapacity) {
        this.hospitalName = title;
        this.doctors = new Doctor[doctorCapacity];
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return doctors.length;
    }


    public boolean treat(Supplier<Doctor> supplier) {
        if (size >= doctors.length) {
            return false;
        }
        doctors[size++] = supplier.get();
        return true;
    }

  
    public void forEach(Consumer<Doctor> action) {
        for (int i = 0; i < size; i++) {
            action.accept(doctors[i]);
        }
    }

   
    public int count(Predicate<Doctor> predicate) {
        int c = 0;
        for (int i = 0; i < size; i++) {
            if (predicate.test(doctors[i])) {
                c++;
            }
        }
        return c;
    }

    public Doctor findFirst(Predicate<Doctor> predicate) {
        for (int i = 0; i < size; i++) {
            if (predicate.test(doctors[i])) {
                return doctors[i];
            }
        }
        return null;
    }

    public Doctor[] filter(Predicate<Doctor> predicate) {
      
        int matches = count(predicate);
        Doctor[] out = new Doctor[matches];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (predicate.test(doctors[i])) {
                out[j++] = doctors[i];
            }
        }
        return out;
    }

    /**
     * Map doctors to Strings (labels) with a Function.
     * (We return String[] to avoid generics + array creation complexity.)
     */
    public String[] mapToLabels(Function<Doctor, String> mapper) {
        String[] out = new String[size];
        for (int i = 0; i < size; i++) {
            out[i] = mapper.apply(doctors[i]);
        }
        return out;
    }

    /**
     * In-place update using a Consumer (mutation allowed).
     * Example: increase level +1, cap at 10.
     */
    public void mutate(Consumer<Doctor> mutator) {
        for (int i = 0; i < size; i++) {
            mutator.accept(doctors[i]);
        }
    }

    public void conditionalMutate(Predicate<Doctor> condition, Consumer<Doctor> mutator) {
        for (int i = 0; i < size; i++) {
            if (condition.test(doctors[i])) {
                mutator.accept(doctors[i]);
            }
        }
    }

    public int countForEvaluation(DoctorEvaluator evaluator) {
        int c = 0;
        for (int i = 0; i < size; i++) {
            if (evaluator.evaluate(doctors[i])) {
                c++;
            }
        }
        return c;
    }

    public Doctor[] evaluate(DoctorEvaluator evaluator) {
        int outSize = countForEvaluation(evaluator);
        Doctor[] out = new Doctor[outSize];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (evaluator.evaluate(doctors[i])) {
                out[j++] = doctors[i];
            }
        }
        return out;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hospital: " + hospitalName + " (" + size + "/" + doctors.length + " doctors)\n");
        for (int i = 0; i < size; i++) {
            sb.append(doctors[i].toString()).append("\n");
        }
        return sb.toString();
    }
}

public class HospitalDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmergencyRoom er = new EmergencyRoom("University Clinic", 10);

        int n = sc.nextInt();

        // Supplier that reads line by line
        Supplier<Doctor> doctorSupplier = () -> {
            int license = sc.nextInt();
            String name = sc.next();
            int level = sc.nextInt();
            int patients = sc.nextInt();
            return new Doctor(license, name, level, patients);
        };

        // Add n doctors to treat using the supplier
        for (int i = 0; i < n; i++) {
            er.treat(doctorSupplier);
        }

        sc.close(); // close scanner after done

        // Print all of the doctors with forEach
        System.out.println("Doctors that are treating:");
        er.forEach(d -> System.out.println(d));

        // Print all doctors that treat using Consumer + forEach
        System.out.println("\n=== All Doctors ===");
        er.forEach(d -> System.out.println(d));

        // Use Functional Interface to filter the good doctors
        // Combine two conditions: more than 20 patients AND high expertise (7+)
        DoctorEvaluator highExpertise = new HighExpertiseEvaluator();
        DoctorEvaluator manyPatients = doctor -> doctor.getPatients() > 20;

        // Combined evaluator
        DoctorEvaluator combinedEvaluator = doctor ->
                highExpertise.evaluate(doctor) && manyPatients.evaluate(doctor);

        Doctor[] passing = er.evaluate(combinedEvaluator);

        System.out.println("\n=== Doctors with higher number of patients and a higher level of expertise ===");
        for (Doctor d : passing) System.out.println(d);

        // Print the chief of the department (level = 10)
        System.out.println("\n=== Chief doctor (level = 10) ===");
        Doctor chief = er.findFirst(d -> d.getLevel() == 10);
        System.out.println(chief != null ? chief : "No chief found");

        // Increase the level of expertise for every doctor by 1
        System.out.println("\n=== Increase all expertise levels by 1 (max 10) ===");
        er.mutate(d -> d.setLevel(d.getLevel() + 1));
        er.forEach(d -> System.out.println(d));

        // Conditional mutation: increase the level of expertise of every doctor that has more than 30 patients
        System.out.println("\n=== Increase the level of expertise of every doctor by 1 ===");
        er.conditionalMutate(d -> d.getPatients() > 30, d -> d.setLevel(d.getLevel() + 1));

        // Map doctors to labels in the format: Name: name, Level: level
        System.out.println("\n=== Map doctors to labels ===");
        String[] labels = er.mapToLabels(d -> String.format("Name: %s, Level: %d", d.getName(), d.getLevel()));
        for (String label : labels) {
            System.out.println(label);
        }
    }
}