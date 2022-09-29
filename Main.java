import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Queue<String> recuirements = new LinkedList<>();
        Queue<String> code = new LinkedList<>();
        Queue<String> pass = new LinkedList<>();
        Queue<String> fail = new LinkedList<>();
        Queue<String> ready = new LinkedList<>();
        Queue<String> verification = new LinkedList<>();


        WorkThread BA = new WorkThread(){
            @Override
            public void run(){
                //for(int i = 1; i <= 3; i++) {
                    Software.recuirementsBA(recuirements, code, fail, ready, 1, pass, verification);
               //}
            }
        };
        WorkThread Dev = new WorkThread(){
            @Override
            public void run(){
                Software.codeDev(code, pass, recuirements, fail, ready, verification);
            }
        };
        WorkThread QA = new WorkThread(){
            @Override
            public void run(){
                Software.passQA(pass, recuirements, code, fail, ready, verification);
            }
        };

        BA.start();
        Dev.start();
        QA.start();

    }
}
