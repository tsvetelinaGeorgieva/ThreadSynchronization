import java.util.Queue;
import java.util.Random;

public class Software {

    //private Queue<String> recuirements;
    //private Queue<String> code;
    //private Queue<String> pass;
    //private Queue<String> fail;
    //private Queue<String> ready;
    //private Queue<String> verification;
    //private int i;

    public synchronized static void recuirementsBA(Queue<String> recuirements, Queue<String> code, Queue<String> fail, Queue<String> ready, int i, Queue<String> pass, Queue<String> verification){
        if(i == 0){
            Random random = new Random();
            int rand = random.nextInt();
            if(rand % 2 == 0){
                System.out.println("BA added " + verification.peek() + " in ready.");
                //synchronized (ready) {
                    ready.offer(verification.peek());
               // }
               // synchronized (verification){
                    verification.poll();
               // }
            }else {
                System.out.println("BA added " + verification.peek() + " in fail.");
               // synchronized (fail) {
                    fail.offer(verification.peek());
               // }
               // synchronized (verification){
                    verification.poll();
               // }
            }
        }else if(i == 5){
            if(recuirements.peek()!= null){
                codeDev(code, pass, recuirements, fail, ready, verification);
                passQA(pass, recuirements, code, fail,  ready, verification);
            }else{recuirements.offer("Task" + i);}
        }else if(recuirements.peek() == null){
            recuirements.offer("Task" + i);
            // i++;
            System.out.println("BA added " + recuirements.peek() + " for development.");
            // synchronized (code){
            code.offer(recuirements.peek());
            // }
            // synchronized (recuirements){
            recuirements.poll();
            // }
        }
    }

    public synchronized static void codeDev(Queue<String> code, Queue<String> pass, Queue<String> recuirements, Queue<String> fail, Queue<String> ready, Queue<String> verification){
        if(code.peek() != null){
            System.out.println("Dev took " + code.peek() + " to implement.");
            System.out.println("Dev added " + code.peek() + " for testing.");
            // synchronized (pass) {
            pass.offer(code.peek());
            //}
            // synchronized (code){
            code.poll();
            //}
        }else {
            recuirementsBA(recuirements, code, fail, ready, 5, pass, verification);
        }

    }

    public synchronized static void passQA(Queue<String> pass, Queue<String> recuirements, Queue<String> code, Queue<String> fail, Queue<String> ready, Queue<String> verification){
       if(pass.peek() != null) {
           System.out.println("QA took " + pass.peek() + " to test.");
           Random random = new Random();
           int gen = random.nextInt();
           if (gen % 2 == 0) {
               System.out.println("QA took " + pass.peek() + " for verification.");
               //synchronized (verification){
               verification.offer(pass.peek());
               // }
               // synchronized (pass){
               pass.poll();
               // }
               recuirementsBA(recuirements, code, fail, ready, 0, pass, verification);
           } else {
               System.out.println("QA took " + pass.peek() + " for editing.");
               System.out.println("Dev took " + pass.peek() + " for refactor.");
               // synchronized (pass){
               pass.offer(pass.peek());
               // }
               // synchronized (pass){
               pass.poll();
               // }
           }
       }else {
           codeDev(code, pass, recuirements, fail, ready, verification);
       }

    }
}
