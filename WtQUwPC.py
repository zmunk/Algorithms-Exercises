# Weighted Quick Union with Path Compression
# with tree visualizer

from tkinter import *
from math import *

class Forest:
    def __init__(self, arr):
        self.arr = arr
        self.forest = self.formatarray()
        self.draw_forest()

    def draw_forest(self):
        ww, hh = 1300, 500
        tk = Tk()
        canv = Canvas(tk, width=ww, height=hh)
        canv.grid()
        trees = []
        width = 0
        height = 0
        for tree_array in self.forest:
            if len(tree_array) == 1:
                tree = Node(tree_array[0], canv)
            else:
                tree = Node(tree_array[0], canv, children=tree_array[1:])
            trees.append(tree)
            width += tree.getwidth() * Node.horizontalpadding
            if height < tree.getheight():
                height = tree.getheight()
        curr_y = hh / 2 - float(height) * Node.verticalpadding / 2
        print height
        # canv.create_line(ww / 2, hh / 2 - height * Node.verticalpadding / 2, ww / 2, hh / 2 + height * Node.verticalpadding / 2)
        curr_x = float(ww) / 2 - float(width) / 2
        for tree in trees:
            twidth = tree.getwidth() * Node.horizontalpadding
            tree.draw_all(curr_x + twidth / 2, curr_y)
            curr_x += twidth
        mainloop()

    def formatarray(self):
        childrenarray = []
        for i in range(len(self.arr)):
            if i != self.arr[i]: continue
            children = self.childrenlist(i)
            if children is not None:
                childrenarray.append([i] + children)
            else:
                childrenarray.append([i])
        return childrenarray

    def childrenlist(self, parent):
        children = []
        for child in range(len(self.arr)):
            if child == parent: continue
            parent_candidate = self.arr[child]
            if parent_candidate == parent:
                grandchildren = self.childrenlist(child)

                if grandchildren is not None:
                    children.append([child] + grandchildren)
                else:
                    children.append([child])
        if len(children) != 0:
            return children
        else:
            return None


class Node:
    radius = 2
    verticalpadding = 70
    horizontalpadding = 2

    def __init__(self, id, canv, children=None):
        self.id = id
        self.canv = canv
        self.children = children
        self.branches = None
        self.getbranches()

    def getbranches(self):
        if self.children is not None:
            self.branches = []
            for child_branch in self.children:
                child = child_branch[0]
                grandchildren = child_branch[1:]
                if len(grandchildren) == 0:
                    self.branches.append(Node(child, self.canv))
                else:
                    self.branches.append(Node(child, self.canv, children=grandchildren))

    def draw_all(self, x, y):
        self.draw_node(x, y)
        if self.branches is None:
            return
        curr_x = x - self.getwidth() * Node.horizontalpadding / 2
        for branch in self.branches:
            branch_width = branch.getwidth() * Node.horizontalpadding
            branch_x = curr_x + branch_width / 2
            branch_y = y + Node.verticalpadding
            self.draw_line(x, y, branch_x, branch_y)
            branch.draw_all(branch_x, branch_y)
            curr_x += branch_width

    def draw_node(self, x, y):
        self.canv.create_oval(x - Node.radius, y - Node.radius,
                              x + Node.radius, y + Node.radius, fill="black")
        # self.canv.create_text(x, y, font="Times 10 bold", fill="white", text=str(self.id))

    def getwidth(self):
        if self.children is None:
            return 1
        width = 0
        for branch in self.branches:
            width += branch.getwidth()
        return width

    def getheight(self):
        if self.children is None:
            return 1
        height = 0
        for branch in self.branches:
            newheight = branch.getheight()
            if height < newheight:
                height = newheight
        return height + 1

    def draw_line(self, x1, y1, x2, y2):
        r = Node.radius
        if x2 < x1:
            x1, x2, y1, y2 = x2, x1, y2, y1
        if x1 == x2:
            x2 = x1 + 0.0001
        th = atan(float(y2 - y1) / (x2 - x1))
        x1, x2, y1, y2 = x1 + r * cos(th), x2 - r * cos(th), \
                         y1 + r * sin(th), y2 - r * sin(th)
        self.canv.create_line(x1, y1, x2, y2)


class WQUPC:
    def __init__(self, n):
        self.count = n
        self.id = range(n)
        self.sz = [1] * n

    def find(self, p):
        root = p
        while root != self.id[root]:
            root = self.id[root]
        while p != root:
            newp = self.id[p]
            self.id[p] = root
            p = newp
        return root

    def union(self, p, q):
        rtP, rtQ = self.find(p), self.find(q)
        if rtP == rtQ: return
        if self.sz[rtP] > self.sz[rtQ]:
            self.id[rtQ] = rtP
            self.sz[rtQ] += self.sz[rtP]
        else:
            self.id[rtP] = rtQ
            self.sz[rtP] += self.sz[rtQ]
        self.count -= 1

    def print_id(self):
        print "\t",
        for node in range(10): print str(node) + "\t",
        print "\nid:\t",
        for id in self.id: print str(id) + "\t",
        print ""


with open("mediumUF.txt", 'r') as mediumUF:
    n = int(mediumUF.readline())
    wtq = WQUPC(n)
    for line in mediumUF:
        p, q = [int(i) for i in line.split(" ")]
        wtq.union(p, q)

Forest(wtq.id)
