package com.chapter15;

/**
 * AdviceSlip Object
 *
 */
public class AdviceSlip {
    private Slip slip;

    AdviceSlip(int slipId, String advice) {
        slip = new Slip(slipId, advice);
    }
    AdviceSlip() {
        slip = new Slip();
    }
    public int getSlipId() {
        return slip.getSlipId();
    }
    public String getAdvice() {
        return slip.getAdvice();
    }


}

class Slip {

    private int slipId;
    private String advice;

    Slip(int slipId, String advice) {
        this.slipId = slipId;
        this.advice = advice;
    }
    Slip() {

    }

    /**
     *
     * @return slipId
     */
    public int getSlipId() {
        return slipId;
    }

    /**
     *
     * @return advice
     */
    public String getAdvice() {
        return advice;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
