package htw_berlin.de.mapmanager.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AdjacencyMatrixGraph extends AbstractGraph {

	@Override
	public void loadFromInputStream(InputStream inputStream) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

			String sCurrentLine;
			// Skip first line
			sCurrentLine = br.readLine();

			int[] firstRow = convertToIntArray(sCurrentLine.split("\t"));

			// Generate empty nodes
			nodes = new ArrayList<>(firstRow.length - 1);
			for (int i = 1; i < firstRow.length; i++) {
				nodes.add(new Node(firstRow[i]));
			}

			Node currentNode;
			while ((sCurrentLine = br.readLine()) != null) {
				int[] data = convertToIntArray(sCurrentLine.split("\t"));
				currentNode = nodes.get(data[0] - 1);

				for (int i = 1; i < data.length; i++) {
					if (data[i] != 0) {
						currentNode.addEdge(nodes.get(i - 1), data[i]);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
