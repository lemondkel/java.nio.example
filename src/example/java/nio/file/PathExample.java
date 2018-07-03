package example.java.nio.file;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class PathExample {
	public static void main(String[] args) {
		Path path = Paths.get("/src/example/java/nio/file/PathExample.java");
		System.out.println("File Name: " + path.getFileName());
		System.out.println("Parent Directory Name: " + path.getParent().getFileName());
		System.out.println("Directory Count: " + path.getNameCount());

		System.out.println();
		for (int i = 0; i < path.getNameCount(); i++) {
			System.out.println(path.getName(i));
		}
		System.out.println();
		Iterator<Path> iterator = path.iterator();
		while (iterator.hasNext()) {
			Path tmp = iterator.next();
			System.out.println(tmp.getFileName());
		}
	}
}
