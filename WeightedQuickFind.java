package pkg;

public class WeightedQuickFind {

	private int[] id;
	private int[] sz;
	private int count;

	public WeightedQuickFind(int n) {
		id = new int[n]; // create id array
		sz = new int[n]; // create size array
		count = n; // number of components
		for (int i = 0; i < n; i++) {
			id[i] = i; // initialize arrays
			sz[i] = 1;
		}
	}

	public int count() {
		return count;
	}

	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}

	public void union(int p, int q) {
		int pRt = id[p];
		int qRt = id[q];
		if (pRt == qRt)
			return;

		int pSz = sz[pRt]; // size of p
		int qSz = sz[qRt]; // size of q
		int newID, oldID; // new id is id of larger component
		if (pSz < qSz) {
			newID = qRt;
			oldID = pRt;
		} else {
			newID = pRt;
			oldID = qRt;
		}

		for (int i = 0; i < id.length; i++)
			if (id[i] == oldID)
				id[i] = newID;

		sz[newID] += sz[oldID];
		count--;
	}

	public static void main(String[] args) {
		WeightedQuickFind QF = new WeightedQuickFind(10);
		QF.union(4, 3);
		QF.union(3, 8);
		QF.union(6, 5);
		QF.union(9, 4);
		QF.union(2, 1);
		QF.union(8, 9);
		QF.union(5, 0);
		QF.union(7, 2);
		QF.union(6, 1);
		QF.union(1, 0);
		QF.union(6, 7);
		System.out.println(QF.count());
	}
}