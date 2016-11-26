import java.util.function.Predicate;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {

    Predicate<String> isD = str -> str.charAt(0) == 'D';


    ArrayList<Note> list = new ArrayList<Note>();
    list.add(new Note(){{name = "Petr"; surname = "Alexeev";}});
    list.add(new Note(){{name = "Dmitry"; surname = "Petrov";}});
    list.add(new Note(){{name = "Vasily"; surname = "Safonov";}});
    list.add(new Note(){{name = "Evgeny"; surname = "Norin";}});
    list.add(new Note(){{name = "Gleb"; surname = "Shishkov";}});

    list.stream().map(note -> note.name).filter(isD).forEach(System.out::println); //:: - ссылка на метод

    for(Note elem : list)
    {

        System.out.println(elem.name + " " + elem.surname);
    }

  }
}


class Note {
    public String name;
    public String surname;

}
