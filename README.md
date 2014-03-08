SOUNDXY4
========
Copyright (c) 2013 Anna Xambó <anna.xambo@open.ac.uk>

Introduction
------------

**SoundXY4** is a musical tabletop interface implemented for real-time performance of sound samples and effects using ambisonics spatialisation. SoundXY4 is suitable for performing in collaboration using a multichannel system – ideally with four speakers. It provides auditory information of the location of tangible objects on a tabletop surface. It can be useful to support both individual and group awareness of users actions on the tabletop surface with no need of using headphones. It can also permit the musicians to explore and discover together a set of sound categories (in this case Russolo's) in the context of music performance. Sounds are grouped in different categories inspired by Russolo's taxonomy of sounds presented in The Art of Noise (1913). The palette of sounds of this version has been selected by Gerard Roma from Freesound.org (see below a full list of sound credits). 

See a video demo here:

http://vimeo.com/70693984


Application Start
-----------------

Before starting the SoundXY4 application, you will need to start your tabletop hardware setup or alternatively a software simulator. This version has been tested with a hardware setup based on the diffused illumination (DI) technique, assuming that i) below the tabletop surface there is an IR camera with appropriate IR illumination and a video projector, and ii) the tabletop surface material is both translucent enough to allow the IR camera to track the markers of the objects, and opaque enough to allow the projection to be clearly displayed. 

For more information: 

http://wiki.nuigroup.com/Diffused_Illumination

Make sure to install a computer vision engine that implements the TUIO protocol (here we are using reacTIVision: http://reactivision.sourceforge.net). Confirm that an IR camera is connected to reacTIVision before launching the engine, and also that in the file `reacTIVision.xml` the number of port is changed to `57120` to send TUIO messages to SuperCollider: `<tuio host="127.0.0.1" port="57120" />`. Alternatively start the TUIO Simulator typing `java -jar TUIOSimulator.jar -port 57120` from the command line to send TUIO messages to SuperCollider.

reacTIVision provides a set of fiducial markers with unique IDs. It is recommended to have a number of physical objects with these markers attached.

The SoundXY4 application is based on the SuperCollider programming language.
 
http://supercollider.sourceforge.net

Drag SoundXY4 folder to the Extensions folder of SuperCollider: `/Users/{username}/Library/Application Support/SuperCollider/Extensions` (in Mac)

You need to have installed the SETO Quark for the recognition of tangible objects.

http://sourceforge.net/p/quarks/code/HEAD/tree/seto/

You will also need to download the sounds and point to the corresponding folder in the main class file `XY4.sc`.

Restart SuperCollider.


Usage
-----

You can start the application by typing `XY4.new` on the SuperCollider IDE. The spatialisation mode is activated by default. It is also possible to launch the program with no spatialisation by typing `XY4.new(0)`. 
 
SoundXY4 tracks the identity (ID), position and orientation of tangible objects tagged with fiducial markers and maps them to sound players or effects. In this version, there is a subset of 36 different sound samples that are mapped to 36 fiducials (IDs from 0 to 35). Sounds are played in loop. Sounds are grouped in 6 categories inspired by Russolo's taxonomy. We used physical cubes with a unique marker on each side of the cube. For this version, we used another subset of 6 filters repeated in 4 different cubes (IDs from 108 to 131). Filters used are a band pass filter, a resonant low pass filter, a high pass filter, a comb delay, a pitch shifter, and an amplitude modulator.
 
 When a sound player cube is on the tabletop surface, it is highlighted by a coloured square. There is a different colour for each category of sound players, and the nearest filter will take the same colour to indicate the sound that it is affecting.
 
It is possible to change the volume for each cube by rotating clockwise (volume up) or counterclockwise (volume down).


License
-------

SoundXY4 is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version. See [COPYING](COPYING) for the license text.


References
----------
- This code is inspired by WaveTable, a tangible and multi-touch tabletop waveform editor developed by Gerard Roma who led the software design and Anna Xambó who led the hardware design in 2008. Read our paper here: http://www.nime.org/proceedings/2008/nime2008_249.pdf

- This code is a follow-up of the project SoundXY2 (http://github.com/axambo/soundxy2), a musical tabletop interface for a stereo system developed by Anna Xambó in 2012. See a video demo here: http://vimeo.com/55298584

- This application runs with SuperCollider: http://supercollider.sourceforge.net

- This application uses the SETO Quark for the recognition of objects, developed by Till Bovermann: http://tuio.lfsaw.de/seto.shtml

- The computer vision engine used in this work is reacTIVision, developed by Martin Kaltenbrunner: http://reactivision.sourceforge.net

- The sound samples used in this application follow the categories proposed by Russolo in 1913 (The Art of Noise). Read the manifesto here: http://www.artype.de/Sammlung/pdf/russolo_noise.pdf

- The selection of sounds has been carried out by Gerard Roma from Freesound.org.

- All sounds (see full list below) can be found in the open sound database Freesound.org, developed by the MTG-UPF: http://freesound.org


Acknowledgements
----------------

I am very thankful to Gerard Roma for his suggestions on how to improve the code and logic of this application. Thanks to the SuperCollider community for developing such a powerful programming tool and for the opportunity to carry out a workshop on tangibles together with Gerard Roma and Till Bovermann in the SuperCollider Symposium 2012, which was the start of a direction followed in this application. Finally, thanks to the Reactable team for the ideas and technologies that have inspired this project.


Sound credits
-------------

**explosions**

- Splash.wav by Kayyy: http://freesound.org/people/Kayyy/sounds/61015/
- SUBSONIC RUMBLE.wav by sandyrb: http://freesound.org/people/sandyrb/sounds/84347/
- Large Crash.wav by CGEffex: http://freesound.org/people/CGEffex/sounds/99960/
- explosion4.wav by sarge4267: http://freesound.org/people/sarge4267/sounds/102734/
- Thunder_close_Boem.wav by hantorio: http://freesound.org/people/hantorio/sounds/121946/
- Gun Shot sound_02 by GregsMedia: http://freesound.org/people/GregsMedia/sounds/150139/

**percussion**

- prac - tom.wav by TicTacShutUp: http://freesound.org/people/TicTacShutUp/sounds/449/
- rocks.aif by splashzooka: http://freesound.org/people/splashzooka/sounds/21789/
- BatonRes.wav by garogourou: http://freesound.org/people/garogourou/sounds/144522/
- Teller 01.wav by Peter Lustig: http://freesound.org/search/?q=150098
- falling metal 1 - 20.3.11.wav by toiletrolltube: http://freesound.org/people/toiletrolltube/sounds/179854/
- Cymbal Impact.wav by LloydEvans09: http://freesound.org/people/LloydEvans09/sounds/185818/

**screeches**

- spoon_bowl2.wav by Corsica_S: http://freesound.org/people/Corsica_S/sounds/64383/
- 00736 rustling plastic 1.wav by Robinhood76: http://freesound.org/people/Robinhood76/sounds/67267/
- Neon Light.wav by Julien Matthey: http://freesound.org/people/Julien%20Matthey/sounds/118340/
- Monster Screech.wav by thegoose09: http://freesound.org/people/thegoose09/sounds/125388/
- train screech.wav by ognito perceptu: http://freesound.org/people/cognito%20perceptu/sounds/181868/
- fire.crackling.mp3 by dobroide: http://freesound.org/people/dobroide/sounds/4211/

**voices**

- tibetan chant 1.wav by djgriffin: http://freesound.org/people/djgriffin/sounds/15362/
- insane laughter man reverb.wav by Leady: http://freesound.org/people/Leady/sounds/26729/
- psycho scream 1.wav by FreqMan: http://freesound.org/people/FreqMan/sounds/42847/
- 01948 kid laughter.wav by Robinhood76: http://freesound.org/people/Robinhood76/sounds/103592/
- catHowling2.wav by Zabuhailo: http://freesound.org/people/Zabuhailo/sounds/146965/
- Groan_male_normalised.wav by Adam_N: http://freesound.org/people/Adam_N/sounds/171758/

**whispers**

- wind_gurgle.ogg by Halleck: http://freesound.org/people/Halleck/sounds/2878/
- CatMathilda01_-18dBFS.wav by DanGasior: http://freesound.org/people/DanGasior/sounds/90742/
- Stomach_Gurgling - SeveralSamples by Vosvoy: http://freesound.org/people/Vosvoy/sounds/149120/
- whisper.wav by Erdie: http://freesound.org/people/Erdie/sounds/165617/
- audience becomes still 01.wav by klankbeeld: http://freesound.org/people/klankbeeld/sounds/171550/
- Whisper of Wind by Hephaestus: http://freesound.org/people/Hephaestus/sounds/181183/

**whistles**

- 00773 leaking gas 1.wav by Robinhood76: http://freesound.org/people/Robinhood76/sounds/66248/
- whistle of boat.aif by nextmaking: http://freesound.org/people/nextmaking/sounds/86045/
- short whistle it tunnel.aiff by SoundCollectah: http://freesound.org/people/SoundCollectah/sounds/109354/
- pipe hiss 003.WAV by DJ Chronos: http://freesound.org/people/DJ%20Chronos/sounds/130289/
- Air_escaping.wav by Adam_N: http://freesound.org/people/Adam_N/sounds/164623/
- Snort 2 long.flac by bigfriendlyjiant: http://freesound.org/people/bigfriendlyjiant/sounds/183915/
