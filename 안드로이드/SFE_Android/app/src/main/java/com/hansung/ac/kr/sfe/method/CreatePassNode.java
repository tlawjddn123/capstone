package com.hansung.ac.kr.sfe.method;

import com.hansung.ac.kr.sfe.model.PassNode;

import java.util.ArrayList;

public class CreatePassNode extends ArrayList {
    private ArrayList<PassNode> nodes;

    public ArrayList<PassNode> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<PassNode> nodes) {
        this.nodes = nodes;
    }

    public CreatePassNode() {
        nodes = new ArrayList<PassNode>();

        nodes.add(new PassNode(1,1,320,400)); //0
        nodes.add(new PassNode(2,2,405,400));
        nodes.add(new PassNode(3,3,490,400)); // 1
        nodes.add(new PassNode(4,4,665,400));
        nodes.add(new PassNode(5,5,840,400));  //2
        nodes.add(new PassNode(6,6,1020,400));
        nodes.add(new PassNode(7,7,1200,400)); //3
        nodes.add(new PassNode(8,8,490,565));
        nodes.add(new PassNode(9,9,490,730));  //4
        nodes.add(new PassNode(10,10,490,1015));
        nodes.add(new PassNode(11,11,490,1300)); //5
        nodes.add(new PassNode(12,12,610,1300));
        nodes.add(new PassNode(13,13,660,1300));// 6
        nodes.add(new PassNode(14,14,610,1750));//7

        nodes.add(new PassNode(15,15,610,1950));
        nodes.add(new PassNode(16,16,610,2150));//8
        nodes.add(new PassNode(17,17,150,2150));//9
        nodes.add(new PassNode(18,18,380,2150));
        nodes.add(new PassNode(19,19,955,2150));
        nodes.add(new PassNode(20,20,1300,2150));//10

    }

}
