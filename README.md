# sparkler-jsdriver

This project explores ajax enabled headless browsing mechanisms for sparkler.

### How to use it?
1. Open `seed.txt` present in `src/test/resources` and add your list of http URLs. One URL per line
2. Use maven to run test cases. `mvn test` (Tested on java 8)
3. Crawled content will be saved in execution directory. One file for each seed URL with file name as urlencoded URL
