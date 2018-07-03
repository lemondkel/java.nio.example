package example.java.nio.file;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class WatchServiceExample {

	public static void main(String[] args) throws IOException, InterruptedException {
		Path path = Paths.get("/src/example/java/nio/file/WatchServiceExample.java");
		WatchService watchService = FileSystems.getDefault().newWatchService();
		path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);

		while (true) {
			WatchKey key = watchService.take();
			List<WatchEvent<?>> eventList = key.pollEvents();
			for (WatchEvent<?> event : eventList) {

				// �̺�Ʈ�� ����
				Kind<?> kind = event.kind();

				// ������ Path
				Path tempPath = (Path) event.context();

				// �̺�Ʈ �������� ó��
				if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
					// �����Ǿ��� ���, ������ �ڵ�
					System.out.println("Created!");
				} else if (kind.equals(StandardWatchEventKinds.ENTRY_DELETE)) {
					// �����Ǿ��� ���, ������ �ڵ�
					System.out.println("Deleted!");
				} else if (kind.equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
					// �����Ǿ������, ������ �ڵ�
					System.out.println("Edited!");
				} else if (kind.equals(StandardWatchEventKinds.OVERFLOW)) {
					// �ü������ �̺�Ʈ�� �ҽǵǾ��ų� ������ ��쿡 �߻�
					System.out.println("Lossed!");
				}

			}
			boolean valid = key.reset();

			if (!valid) {
				break;
			}
		}
		watchService.close();
	}
}
