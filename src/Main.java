

public class Main {

    public static void main(String[] args) {
        MessData[] messData=new MessData[]{
                new Behavior("_behavior"),
                new OverView("_overview"),
                new NetWork("_network"),
        };
//        for(MessData md:messData){
//            md.deal();
//        }
        messData[2].deal();

    }

}
