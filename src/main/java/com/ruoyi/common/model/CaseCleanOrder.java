package com.ruoyi.common.model;

import java.util.List;

/**
 * @author wangsr.fnst
 */
public class CaseCleanOrder {
    short caseNumber;
    List<String> casePath;

    /**
     * Gets the value of caseNumber
     *
     * @return the value of caseNumber
     */
    public short getCaseNumber() {
        return caseNumber;
    }

    /**
     * Sets the caseNumber
     * You can use getCaseNumber() to get the value of caseNumber
     *
     * @param caseNumber caseNumber
     */
    public void setCaseNumber(short caseNumber) {
        this.caseNumber = caseNumber;
    }

    /**
     * Gets the value of casePath
     *
     * @return the value of casePath
     */
    public List<String> getCasePath() {
        return casePath;
    }

    /**
     * Sets the casePath
     * You can use getCasePath() to get the value of casePath
     *
     * @param casePath casePath
     */
    public void setCasePath(List<String> casePath) {
        this.casePath = casePath;
    }

    @Override
    public String toString() {
        return "CaseOrder{" +
                "caseNumber=" + caseNumber +
                ", casePath=" + casePath +
                '}';
    }
}
