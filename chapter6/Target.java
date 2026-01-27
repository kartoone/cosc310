package chapter6;

abstract public class Target {
    protected int arr[];
    protected ArrayList<Integer> list;
    protected String name;

    //method under test
    //indicesOrnums is being used for two different purposes
    abstract public int method(int[] indicesOrnums);

}
