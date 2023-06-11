package common;

public class DataElements {

    public enum Shapes{
        rectangle(0), oval(1), freeLine(2), none(3);

        private final int value;

        private Shapes(int shape){
            this.value = shape;
        }

        public int getValue(){
            return value;
        }
    }

    public enum Tools{
        rectangle(0), oval(1), freeLine(2), drag(3), none(4);

        private final int value;

        private Tools(int tool){
            this.value = tool;
        }

        public int getValue(){
            return value;
        }
    }
}
