
public class TestPrinter<T> {
    private int totalStringLength = 50;
    
    public void test(T testValue, T expected, String testName) {
        StringBuffer sBuffer = new StringBuffer();
        sBuffer.append(testName);

        int numSpaces = totalStringLength - testName.length() - "passed".length();
        for (int i = 0; i < numSpaces; i++) sBuffer.append(" ");

        if (testValue == expected) sBuffer.append("passed");
        else {
            sBuffer.append(String.format("failed \n value: %d, expected: %d", testValue, expected));
        }

        System.out.println(sBuffer.toString());
    }

    public static void main(String[] args) {
        TestPrinter<Integer> printer = new TestPrinter<Integer>();
        printer.test(5, 5, "testing integers: ");
        printer.test(5, 1, "testing integers should fail: ");
    }

}