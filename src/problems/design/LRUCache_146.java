package problems.design;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @User: 吴广谋
 * @Date: 2020/5/14
 * @Description: 运用你所掌握的数据结构，设计和实现一个LRU(最近最少使用)缓存机制。它应该支持以下操作：获取数据get和写入数据put。
 * 获取数据 get(key)：如果key存在于缓存中，则获取value的值（总是正数），否则返回-1。
 * 写入数据 put(key, value)：如果key已经存在，则变更其value值；如果key不存在，则插入该组（key，value）。当缓存容量达到上限时，
 * 它应该在写入新数据之前删除最久未使用的value值，从而为新的数据值留出空间
 * 进阶：你是否可以在 O(1)时间复杂度内完成这两种操作？
 */
public class LRUCache_146 {

    static class LRUCache extends LinkedHashMap<Integer, Integer>{

        private int capacity;

        public LRUCache(int capacity) {
            //使用LinkedHashMap来构建缓存，按访问顺序来排序（每个访问过的元素都会重新放到队尾）
            super(capacity, 0.75f, true);
            this.capacity = capacity;

        }

        public int get(int key) {
            //取出缓存，若没有对应的value，返回-1
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            //当LinkedHashMap中元素的容量超过最大值时，会移除最老的元素，即头节点的元素
            return size() > capacity;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        System.out.println(cache.get(1));
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        System.out.println(cache.get(2));
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        System.out.println(cache.get(1));
        cache.get(3);       // 返回  3
        System.out.println(cache.get(3));
        cache.get(4);       // 返回  4
        System.out.println(cache.get(4));

    }
}
