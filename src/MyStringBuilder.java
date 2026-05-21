import java.util.Arrays;

public class MyStringBuilder {
    private char[] buffer;
    private int length;
    private ExchangeMemento lastState;

    public MyStringBuilder() {
        this.buffer = new char[16];
        this.length = 0;
    }

    public MyStringBuilder(int capacity) {
        this.buffer = new char[capacity];
        this.length = 0;
    }

    public MyStringBuilder append(String str) {
        if (str == null) return this;

        lastState = save();

        int strLen = str.length();
        ensureCapacity(length + strLen);

        for (int i = 0; i < strLen; i++) {
            buffer[length + i] = str.charAt(i);
        }
        length += strLen;
        return this;
    }

    public MyStringBuilder append(int num) {
        return append(String.valueOf(num));
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > buffer.length) {
            char[] newBuffer = new char[Math.max(buffer.length * 2, minCapacity)];
            System.arraycopy(buffer, 0, newBuffer, 0, length);
            buffer = newBuffer;
        }
    }

    public int length() {
        return length;
    }

    public int capacity() {
        return buffer.length;
    }

    @Override
    public String toString() {
        return new String(buffer, 0, length);
    }

    public ExchangeMemento save() {
        return new ExchangeMemento(Arrays.copyOf(buffer, buffer.length), length);
    }

    public void restore(IMemento exchangeMemento) {
        buffer = exchangeMemento.getChars();
        length = exchangeMemento.getLength();
    }

    public String undo() {
        if (lastState == null) return toString();

        restore(lastState);
        lastState = null;
        return toString();
    }
}

interface IMemento {
    char[] getChars();
    int getLength();
}

class ExchangeMemento implements IMemento {
    private char[] buffer;
    private int length;

    public ExchangeMemento(char[] buffer, int length) {
        this.buffer = buffer;
        this.length = length;
    }

    @Override
    public char[] getChars() {
        return this.buffer;
    }

    @Override
    public int getLength() {
        return this.length;
    }
}
