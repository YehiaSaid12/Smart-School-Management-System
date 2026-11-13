package netbeans_school;
/* 
CHANGES
--> "code" data field made into final
--> added if/throw block
--> removed setCode method ( code cannot be changed after creation as it affects the rest of the program )
--> setWeeklyPeriods and setName methods changed to become more strict  (بقى ليها شروط معينا)
--> overriden "matches" method changed to become more efficient
--> incorrectly overriden methods removed and replaced with more suitable ones

*/
public class Subject implements Searchable {
    private final String code;
    private String name;
    private int weeklyPeriods;

    public Subject(String code, String name, int weeklyPeriods) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject code cannot be blank.");
        }

        this.code = code.trim().toUpperCase(); // --> uppercase avoids later confusion with codes, "ENG" and "eng" could possibly not be the same code
        setName(name);
        setWeeklyPeriods(weeklyPeriods);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getWeeklyPeriods() {
        return weeklyPeriods;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject name cannot be blank.");
        }
        this.name = name.trim();
    }

    public void setWeeklyPeriods(int weeklyPeriods) {
        if (weeklyPeriods < 0) {
            throw new IllegalArgumentException("Weekly periods cannot be negative.");
        }
        this.weeklyPeriods = weeklyPeriods;
    }

    @Override
    public boolean matches(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return true; // if the string is empty i dont want the code below to execute! ( because theres no point )
        }

        String key = keyword.toLowerCase();
        return code.toLowerCase().contains(key)
                || name.toLowerCase().contains(key);
    }

   public boolean hasSameCode(Subject other) {

    if (other == null) {
        return false;
    }

    return code.equals(other.getCode());
}

    public String getDisplayText() {
        return code + " - " + name;
    }
}
