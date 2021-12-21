package com.oht.Data;

public class Step {
    private String stepTitle, subTitle, stepContent;

    public Step(String stepTitle, String stepContent) {
        this.stepTitle = stepTitle;
        this.stepContent = stepContent;
    }

    public Step(String stepTitle, String subTitle, String stepContent) {
        this.stepTitle = stepTitle;
        this.subTitle = subTitle;
        this.stepContent = stepContent;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getStepTitle() {
        return stepTitle;
    }

    public void setStepTitle(String stepTitle) {
        this.stepTitle = stepTitle;
    }

    public String getStepContent() {
        return stepContent;
    }

    public void setStepContent(String stepContent) {
        this.stepContent = stepContent;
    }
}
