

public class Main {

    public static void main(String[] args) {
        MessData[] messData=new MessData[]{
                new Behavior("_behavior"),
                new OverView("_overview"),
        };
//        for(MessData md:messData){
//            md.deal();
//        }
//        messData[1].dealDebug();
        messData[0].deal();

    }

}
