package com.skrinternationals.theclicker;

public class Counter {
    private int m_value;

    public Counter() {
        m_value = 0;
    }

    public int getValue() {
        return m_value;
    }

    public void setValue(int value) {
        m_value = value;
    }

    void increment () {
        m_value++;
    }
}

