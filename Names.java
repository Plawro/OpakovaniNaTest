public class Names{
    String name;
    boolean yesOrNo;

    public Names(String name, boolean yesOrNo){
        this.name = name;
        this.yesOrNo = yesOrNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isYesOrNo() {
        return yesOrNo;
    }

    public void setYesOrNo(boolean yesOrNo) {
        this.yesOrNo = yesOrNo;
    }
}
