package week14.practice.task3;


import java.io.*;

public class SerializationDemo {
    public static void main(String[] args) {
        Student stin = new Student("Ivan", 20, "mypassword");
        String fileName = "student.ser";
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            oos.writeObject(stin);
            System.out.println("File has been written");
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName)))
        {
            Student stout = (Student) ois.readObject();
            System.out.printf("Name: %s \t Age: %d \tPassword: %s \n", stout.getName(), stout.getAge(), stout.getPassword());
        }
        catch (IOException | ClassNotFoundException ex) {
            System.out.println("Ошибка при десериализации: " + ex.getMessage());
            ex.printStackTrace(); // Для отладки полезно видеть stack trace
        }
    }
}