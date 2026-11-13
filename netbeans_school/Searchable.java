package netbeans_school;
// no new changes in this file 

public interface Searchable {
    boolean matches(String keyword); /* the abstract method will be filled out by all classes implementing searchable where we add our own conditions.
    For example, lets say we have a student called "ahmed hassan" and we overrode the method and its body is now
    
    public boolean matches(String keyword) {
    return getName().toLowerCase()
            .contains(keyword.toLowerCase());
}
    if i write student.matches("ahmed"), it will detect ahmed hassan because of the keyword "ahmed" ( method returns true )
    */
}
