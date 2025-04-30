public class ConcatenateStrings {

    public static String concatenateStrings(String[] strings) {
        // Step 1: Initialize a new StringBuffer object
        StringBuffer stringBuffer = new StringBuffer();

        // Step 2: Iterate through each string in the array and append it to the StringBuffer
        for (String str : strings) {
            stringBuffer.append(str);
        }

        // Step 3: Return the concatenated string
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        // Example input array of strings
        String[] strings = {"Hello", " ", "World", "!", " How", " are", " you?"};

        // Call the concatenateStrings method and print the result
        String result = concatenateStrings(strings);
        System.out.println("Concatenated String: " + result);
    }
}