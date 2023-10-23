package hello;

import java.util.*;

public class Main {

    class Solution {

        class Melody{
            int times;
            String title;
            int order;
            public Melody(int times, String title, int order){
                this.times = times;
                this.title = title;
            }
        }

        private Map<String, String> melodyMap = new HashMap<>();

        public void init(){
            melodyMap.put("A", "1");
            melodyMap.put("A#", "2");
            melodyMap.put("B", "3");
            melodyMap.put("C", "4");
            melodyMap.put("C#", "5");
            melodyMap.put("D", "6");
            melodyMap.put("D#", "7");
            melodyMap.put("E", "8");
            melodyMap.put("F", "9");
            melodyMap.put("F#", "10");
            melodyMap.put("G", "11");
            melodyMap.put("G#", "12");
        }

        public String solution(String m, String[] musicinfos) {
            String answer = "";

            init();

            PriorityQueue<Melody> pq = new PriorityQueue<>(
                (a, b) -> {
                    if (a.times == b.times){
                        return Integer.compare(a.order, b.order);
                    }
                    return Integer.compare(b.times, a.times);
                }
            );

            int order = 0;
            for (String info : musicinfos){
                String[] split = info.split(",");
                int times = getTimes(split[0], split[1]);
                String melody = getMelody(split[3], times);
                String target = getMelody(m);

                if (melody.contains(target)){
                    pq.offer(new Melody(times, split[2], order));
                }
                order++;
            }

            if (pq.isEmpty()){
                return "(None)";
            }
            return pq.poll().title;
        }

        public int getTimes(String start, String end){
            String[] startSplit = start.split(":");
            String[] endSplit = end.split(":");
            int startHour = Integer.parseInt(startSplit[0]);
            int endHour = Integer.parseInt(endSplit[0]);
            int startMin = Integer.parseInt(startSplit[1]);
            int endMin = Integer.parseInt(endSplit[1]);
            int times = (endHour - startHour) * 60 + (endMin - startMin);

            return times;
        }

        public String getMelody(String m){
            return transferToString(m);
        }

        public String getMelody(String melody, int times){
            List<String> sheet = transferToArray(melody);

            StringBuilder sb = new StringBuilder();
            int mi = 0;
            int len = sheet.size();
            for (int i=0; i < times; i++){
                if (mi >= len){
                    mi = 0;
                }
                sb.append(sheet.get(mi));
                mi++;
            }
            return sb.toString();
        }

        public String transferToString(String melody){
            Stack<String> stack = transferToStack(melody);

            String sheet = "";
            while(!stack.isEmpty()){
                sheet += stack.pop();
            }
            return sheet;
        }

        public List<String> transferToArray(String melody){
            List<String> list = new ArrayList<>();

            Stack<String> stack = transferToStack(melody);

            while(!stack.isEmpty()){
                list.add(stack.pop());
            }

            return list;
        }

        public Stack<String> transferToStack(String melody){
            Stack<String> stack = new Stack<>();
            for (char ch : melody.toCharArray()){
                stack.push(String.valueOf(ch));
            }

            Stack<String> stack2 = new Stack<>();
            while(!stack.isEmpty()){
                String now = stack.pop();
                if (now.equals("#")){
                    String target = stack.pop() + now;
                    stack2.push(melodyMap.get(target));
                } else {
                    stack2.push(melodyMap.get(now));
                }
            }
            return stack2;
        }
    }
}