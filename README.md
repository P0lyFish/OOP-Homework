# Dependencies
javazoom:
You can download here: http://www.javazoom.net/javalayer/sources.html
Or using maven:
```
<!-- https://mvnrepository.com/artifact/javazoom/jlayer -->
<dependency>
    <groupId>javazoom</groupId>
    <artifactId>jlayer</artifactId>
    <version>1.0.1</version>
</dependency>

<dependency>
    <groupId>net.sourceforge.tess4j</groupId>
    <artifactId>tess4j</artifactId>
    <version>4.5.3</version>
</dependency>
```

# Installation
To use the CLI version, choose src.main.java.cli.CommandlineInterface as the main class.

To use the GUI version, choose src.main.java.gui.Interface as the main class.

Install tesseract following this instruction: https://github.com/tesseract-ocr/tesseract/wiki#windows

Download this: https://github.com/tesseract-ocr/tessdata and copy it to resources folder.

# Features:
- [x] insertFromCommandline
- [x] showAllWords
- [x] dictionaryBasic
- [x] insertFromFile
- [x] Word lookup
- [x] dictionaryAdvance
- [x] insert/remove data from commandline
- [x] dictionarySearcher
- [x] dictionaryExportToFile
- [x] User-friendly GUI
- [x] Optimize word searching algorithm
- [x] Online word pronouncing
- [x] Word suggestion
- [x] OCR
