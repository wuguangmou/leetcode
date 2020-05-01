package base.algorithm.search;

import java.util.*;

/**
 * @User: 吴广谋
 * @Date: 2020/4/28
 * @Description: DFS代码演示，学习来源：bilibili Up主正月点灯笼
 * 视频地址：https://www.bilibili.com/video/BV1Ks411575U/?spm_id_from=333.788.videocard.0
 */
public class DeepFirstSearch {

    /**
     * DFS算法实现
     * @param: graph 无向图，用Map表示，key为点前节点，value为当前节点的所有邻接点
     * @param: s 表示DFS开始的节点
     */
    public static void DFS(Map<String, String[]> graph, String s){
        Stack<String> stack = new Stack<>();        //定义一个栈，用来存储DFS的遍历节点
        stack.add(s);

        List<String> seen = new LinkedList<>();     //定义一个集合，用于保存遍历过程中出现的节点
        seen.add(s);
        while (!stack.empty()){
            String v = stack.pop();
            String[] nodes = graph.get(v);          //获得当前节点的所有邻接点
            for (String w : nodes) {
                if (!seen.contains(w)){             //如果当前节点V的邻接点没有见过
                    stack.add(w);                   //则将当前节点入栈，并且更新数组状态
                    seen.add(w);
                }
            }
            System.out.println(v);                  //输出DFS遍历的节点顺序
        }
    }

    public static void main(String[] args) {
        Map<String, String[]> graph = new HashMap<>();
        graph.put("A", new String[]{"B", "C"});
        graph.put("B", new String[]{"A", "C", "D"});
        graph.put("C", new String[]{"A", "B", "D", "E"});
        graph.put("D", new String[]{"B", "C", "E", "F"});
        graph.put("E", new String[]{"C", "D"});
        graph.put("F", new String[]{"D"});

        DFS(graph, "A");        //从A节点出发，开始DFS遍历
    }
}
