package base.algorithm.search;

import java.util.*;

/**
 * @User: 吴广谋
 * @Date: 2020/4/28
 * @Description: BFS代码演示，学习来源：bilibili Up主正月点灯笼
 *  视频地址：https://www.bilibili.com/video/BV1Ks411575U/?spm_id_from=333.788.videocard.0
 */
public class BreadthFirstSearch {

    public static void BFS(Map<String, String[]> graph, String s){
        LinkedList<String> queue = new LinkedList<>();       //定义一个队列，用来存储BFS的遍历节点
        queue.add(s);

        List<String> seen = new LinkedList<>();             //定义一个集合，用于保存遍历过程中出现的节点
        seen.add(s);
        while (queue.size() > 0){
            String v = queue.pop();
            String[] nodes = graph.get(v);                  //获取当前节点的所有邻接点
            for (String node : nodes) {
                if (!seen.contains(node)){                  //如果当前节点V的邻接点没有见过
                    queue.add(node);                        //则将当前节点插入队尾，并且更新数组状态
                    seen.add(node);
                }
            }
            System.out.println(v);                          //输出BFS遍历的节点顺序
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

        BFS(graph, "A");        //从A节点出发，开始BFS遍历
    }
}
