package com.springboot.xuhui.huawei;

import com.alibaba.fastjson.JSON;

import javax.xml.ws.soap.Addressing;
import java.util.*;
import java.util.zip.Inflater;

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
