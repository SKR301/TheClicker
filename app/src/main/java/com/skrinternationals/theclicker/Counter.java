package com.skrinternationals.theclicker;

public class Counter {
    private int m_value;
    private int m_goal;

    public Counter() {
        m_value = 0;
    }

    final public int getValue() {
        return m_value;
    }

    final public int getGoal() {
        return m_goal;
    }

    public void setValue(int value) {
        m_value = value;
    }

    public void setGoal(int value) {
        m_goal = value;
    }

    void increment () {
        m_value++;
    }

    final boolean isGoalReach() {
        return m_value == m_goal;
    }

    final public int getDifference() {
        return m_goal - m_value;
    }
}

