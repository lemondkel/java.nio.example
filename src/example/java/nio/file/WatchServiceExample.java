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

				// 이벤트의 종류
				Kind<?> kind = event.kind();

				// 감지된 Path
				Path tempPath = (Path) event.context();

				// 이벤트 종류별로 처리
				if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
					// 생성되었을 경우, 실행할 코드
					System.out.println("Created!");
				} else if (kind.equals(StandardWatchEventKinds.ENTRY_DELETE)) {
					// 삭제되었을 경우, 실행할 코드
					System.out.println("Deleted!");
				} else if (kind.equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
					// 수정되었을경우, 실행할 코드
					System.out.println("Edited!");
				} else if (kind.equals(StandardWatchEventKinds.OVERFLOW)) {
					// 운영체제에서 이벤트가 소실되었거나 버려진 경우에 발생
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
