

public class Main {

    public static void main(String[] args) {
        MessData[] messData=new MessData[]{
                new Behavior("_behavior"),
                new OverView("_OverView"),
        };
        for(MessData md:messData){
            md.deal();
        }
//        messData[0].dealDebug();
    }

}
