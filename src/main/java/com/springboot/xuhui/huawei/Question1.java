package com.springboot.xuhui.huawei;

import com.alibaba.fastjson.JSON;

import javax.xml.ws.soap.Addressing;
import java.util.*;
import java.util.zip.Inflater;

/**
 * 	处理器问题、AI处理器组合
 *
 * 某公司研发了一款高性能AI处理器。每台物理设备具备8颗AI处理器，编号分别为0、1、2、3、4、5、6、7。
 * 编号0-3的处理器处于同一个链路中，编号4-7的处理器处于另外一个链路中，不通链路中的处理器不能通信。
 * 如下图所示。现给定服务器可用的处理器编号数组array，以及任务申请的处理器数量num，找出符合下列亲和性调度原则的芯片组合。
 * 如果不存在符合要求的组合，则返回空列表。
 *
 * 亲和性调度原则：
 * -如果申请处理器个数为1，则选择同一链路，剩余可用的处理器数量为1个的最佳，其次是剩余3个的为次佳，然后是剩余2个，最后是剩余4个。
 * -如果申请处理器个数为2，则选择同一链路剩余可用的处理器数量2个的为最佳，其次是剩余4个，最后是剩余3个。
 * -如果申请处理器个数为4，则必须选择同一链路剩余可用的处理器数量为4个。
 * -如果申请处理器个数为8，则申请节点所有8个处理器。
 * 提示：
 * 任务申请的处理器数量只能是1、2、4、8。
 * 编号0-3的处理器处于一个链路，编号4-7的处理器处于另外一个链路。
 * 处理器编号唯一，且不存在相同编号处理器。
 * 输入描述
 * 输入包含可用的处理器编号数组array，以及任务申请的处理器数量num两个部分。
 * 第一行为array，第二行为num。例如：
 *
 * [0, 1, 4, 5, 6, 7]
 * 1
 *
 * 表示当前编号为0、1、4、5、6、7的处理器可用。任务申请1个处理器。
 *
 * 0 <= array.length <= 8
 * 0 <= array[i] <= 7
 * num in [1, 2, 4, 8]
 * 输出描述
 * 输出为组合列表，当array=[0，1，4，5，6，7]，num=1 时，输出为[[0], [1]]。
 *
 * 用例
 * 输入	[0, 1, 4, 5, 6, 7]
 * 1
 * 输出	[[0], [1]]
 * 说明
 * 根据第一条亲和性调度原则，在剩余两个处理器的链路（0, 1, 2, 3）中选择处理器。
 * 由于只有0和1可用，则返回任意一颗处理器即可。
 *
 * 输入	[0, 1, 4, 5, 6, 7]
 * 4
 * 输出	[[4, 5, 6, 7]]
 * 说明	根据第三条亲和性调度原则，必须选择同一链路剩余可用的处理器数量为4个的环
 *
 * 题目解析
 * 用例中，链路link1=[0,1]，链路link2=[4,5,6,7]
 * 现在要选1个处理器，则需要按照亲和性调度原则
 * 如果申请处理器个数为1，则选择同一链路，剩余可用的处理器数量为1个的最佳，其次是剩余3个的为次佳，然后是剩余2个，最后是剩余4个。
 * 最佳的是，找剩余可用1个处理器的链路，发现没有，link1剩余可用2，link2剩余可用4
 * 其次的是，找剩余可用3个处理器的链路，发现没有
 * 再次的是，找剩余可用2个处理器的链路，link1符合要求，即从0和1处理器中任选一个，有两种选择，可以使用dfs找对应组合。
 */
public class Question1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] ints = Arrays.asList(scanner.nextLine().split("[\\[\\]\\,\\s]")).stream().mapToInt(Integer::parseInt).toArray();
        int num = scanner.nextInt();

        System.out.println(getResult(ints, num));
    }

    private static String getResult(int[] ints, int num) {

        List<Integer> link1 = new ArrayList<>();
        List<Integer> link2 = new ArrayList<>();
        for (int anInt : ints) {
            if (anInt < 4) {
                link1.add(anInt);
            } else {
                link2.add(anInt);
            }
        }
        int size1 = link1.size();
        int size2 = link2.size();
        List<List<Integer>> result = new ArrayList<>();
        switch (num) {
            case 1: {
                if (size1 == 1 || size2 == 1) {
                    if (size1 == 1) {
                        buildResult(link1, 0, num, new ArrayList<>(), result);
                    }
                    if (size2 == 1) {
                        buildResult(link1, 0, num, new ArrayList<>(), result);
                    }
                } else if (size1 == 3 || size2 == 3) {
                    if (size1 == 3) {
                        buildResult(link1, 0, num, new ArrayList<>(), result);
                    }
                    if (size2 == 3) {
                        buildResult(link1, 0, num, new ArrayList<>(), result);
                    }
                } else if (size1 == 2 || size2 == 2) {
                    if (size1 == 2) {
                        buildResult(link1, 0, num, new ArrayList<>(), result);
                    }
                    if (size2 == 2) {
                        buildResult(link1, 0, num, new ArrayList<>(), result);
                    }
                } else if (size1 == 4 || size2 == 4) {
                    if (size1 == 4) {
                        buildResult(link1, 0, num, new ArrayList<>(), result);
                    }
                    if (size2 == 4) {
                        buildResult(link1, 0, num, new ArrayList<>(), result);
                    }
                }
                break;
            }
            case 2: {
                if (size1 == 2 || size2 == 2) {
                    if (size1 == 2) {
                        buildResult(link1, 0, num, new ArrayList<>(), result);
                    }
                    if (size2 == 2) {
                        buildResult(link1, 0, num, new ArrayList<>(), result);
                    }
                } else if (size1 == 4 || size2 == 4) {
                    if (size1 == 4) {
                        buildResult(link1, 0, num, new ArrayList<>(), result);
                    }
                    if (size2 == 4) {
                        buildResult(link1, 0, num, new ArrayList<>(), result);
                    }
                } else if (size1 == 3 || size2 == 3) {
                    if (size1 == 3) {
                        buildResult(link1, 0, num, new ArrayList<>(), result);
                    }
                    if (size2 == 3) {
                        buildResult(link1, 0, num, new ArrayList<>(), result);
                    }
                }
                break;
            }
            case 4: {
                if (size1 == 4) {
                    result.add(link1);
                }
                if (size2 == 4) {
                    result.add(link2);
                }
                break;
            }
            case 8: {
                if (size1 == 4 && size2 == 4) {
                    List<Integer> full = new ArrayList<>();
                    full.addAll(link1);
                    full.addAll(link2);
                    result.add(full);
                }
                break;
            }
        }
        return result.toString();

    }

    private static void buildResult(List<Integer> link, int index, int num, ArrayList<Integer> path, List<List<Integer>> result) {

        if (path.size() == num) {
            result.add((List<Integer>) path.clone());
        }

        for (int i = index; i < link.size(); i++) {
            path.add(link.get(i));
            buildResult(link, index + 1, num, path, result);
            path.remove(path.size() - 1);
        }
    }
}
