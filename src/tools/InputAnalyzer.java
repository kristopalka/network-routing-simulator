package tools;

public class InputAnalyzer
{

    public static String[] parseInputCommand(String inputAction)
    {
        //todo

        inputAction.toLowerCase();
        String[] input = inputAction.split(" ");


        return input;
    }

    public static void printArray(String[] array)
    {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            sb.append("_");
        }
        String str = sb.toString();
        System.out.println(str);
    }
}
