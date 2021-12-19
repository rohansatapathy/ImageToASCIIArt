# ImageToASCIIArt
A command line program that generates ASCII art from images. ASCII art is a form of art where images are created using characters displayed in a monospace font, 
like this:

```
                                                                                                
                                                                                                
   ,---,       .--.--.     ,----..     ,---,   ,---,           ,---,                    ___     
  '  .' \     /  /    '.  /   /   \ ,`--.' |,`--.' |          '  .' \                 ,--.'|_   
 /  ;    '.  |  :  /`. / |   :     :|   :  :|   :  :         /  ;    '.      __  ,-.  |  | :,'  
:  :       \ ;  |  |--`  .   |  ;. /:   |  ':   |  '        :  :       \   ,' ,'/ /|  :  : ' :  
:  |   /\   \|  :  ;_    .   ; /--` |   :  ||   :  |        :  |   /\   \  '  | |' |.;__,'  /   
|  :  ' ;.   :\  \    `. ;   | ;    '   '  ;'   '  ;        |  :  ' ;.   : |  |   ,'|  |   |    
|  |  ;/  \   \`----.   \|   : |    |   |  ||   |  |        |  |  ;/  \   \'  :  /  :__,'| :    
'  :  | \  \ ,'__ \  \  |.   | '___ '   :  ;'   :  ;        '  :  | \  \ ,'|  | '     '  : |__  
|  |  '  '--' /  /`--'  /'   ; : .'||   |  '|   |  '        |  |  '  '--'  ;  : |     |  | '.'| 
|  :  :      '--'.     / '   | '/  :'   :  |'   :  |        |  :  :        |  , ;     ;  :    ; 
|  | ,'        `--'---'  |   :    / ;   |.' ;   |.'         |  | ,'         ---'      |  ,   /  
`--''                     \   \ .'  '---'   '---'           `--''                      ---`-'   
                           `---`                                                                
```
This program works by getting an array of RGB values from the pixels of an image, converting that RGB array to an array of brightness values, then mapping 
each brightness value to an ASCII character. For example, a darker pixel might be represented by a `` ` ``  but a brighter pixel will be represented by `$`.
This program supports colored output as well via the `JColor` Library. 


## Installation

To run this program, you need to have a JRE installed. It is recommended to install OpenJDK 17 if possible. The method to do this varies per system, but official
releases can be found [here](https://jdk.java.net/17/). Once you have installed the JRE, head over to find the 
[latest release](https://github.com/rohansatapathy/ImageToASCIIArt/releases/latest) and download the file `ImageToASCIIArt.jar`. Move the file into a directory
with images which you would like to convert to ASCII art. 

For Windows users, it is also recommended to have [Windows Terminal](https://www.microsoft.com/en-us/p/windows-terminal/9n0dx20hk701?activetab=pivot:overviewtab)
installed if you wish to have colored ASCII art. 

## Usage

```
usage: java -jar ImageToASCIIArt.jar [options] --input-file <file>
Convert image file to ASCII art and print it to the terminal

 -c,--color                         Print ASCII art in color
 -h,--help                          Display help information
 -i,--input-file <file>             Use given file to generate ASCII art
 -p,--pixel-character-width <int>   Number of characters per pixel; the
                                    optimal value will depend on your
                                    terminal font and viewing window
                                    [default: 3]
 -w,--width <int>                   Rescale the ASCII art to the given
                                    width in characters [default: 80]

Please report issues at https://github.com/rohansatapathy/ImageToASCIIArt
```

## Contributing

If you run into any trouble using this program, please file an issue so I can resolve it. If you would like to contribute, feel free to fork the repo and 
file a PR! This is one of my first projects using Java and any help would be appreciated. 

## License

Copyright (C) 2021 Rohan Satapathy. Code released under MIT License. See LICENSE for more details. 
