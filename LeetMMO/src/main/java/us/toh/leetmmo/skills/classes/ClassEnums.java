package us.toh.leetmmo.skills.classes;

import java.util.HashMap;
import java.util.Map;

public class ClassEnums {

    public enum ClassType {
        NONE("NONE"),
        CLERIC("CLERIC"),
        PALADIN("PALADIN"),
        ROGUE("ROGUE");

        private String classType;

        ClassType(String classType) {
            this.classType = classType;
        }

        public String toString() {
            return classType;
        }

        //****** Reverse Lookup Implementation************//

        //Lookup table
        private static final Map<String, ClassEnums.ClassType> lookup = new HashMap<>();

        //Populate the lookup table on loading time
        static
        {
            for(ClassEnums.ClassType env : ClassEnums.ClassType.values())
            {
                lookup.put(env.toString(), env);
            }
        }

        //This method can be used for reverse lookup purpose
        public static ClassEnums.ClassType get(String url)
        {
            return lookup.get(url);
        }
    }

    public enum Rogue {
        SWIFTNESS("SWIFTNESS"),
        BACKSTAB("BACKSTAB");

        private String Rogue;

        Rogue(String Rogue) {
            this.Rogue = Rogue;
        }

        public String toString() {
            return Rogue;
        }

        //****** Reverse Lookup Implementation************//

        //Lookup table
        private static final Map<String, ClassEnums.Rogue> lookup = new HashMap<>();

        //Populate the lookup table on loading time
        static
        {
            for(ClassEnums.Rogue env : ClassEnums.Rogue.values())
            {
                lookup.put(env.toString(), env);
            }
        }

        //This method can be used for reverse lookup purpose
        public static ClassEnums.Rogue get(String url)
        {
            return lookup.get(url);
        }
    }
}
