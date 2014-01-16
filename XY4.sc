/*

	SoundXY4 - a musical tabletop interface for ambisonics spatialisation

	Author:
    Copyright (c) 2013 Anna Xambó <anna.xambo@open.ac.uk>
	Computing Department
	Faculty of Maths, Computing and Technology
	The Open University
	Milton Keynes, UK

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program. If not, see <http://www.gnu.org/licenses/>.

*/

XY4{

	classvar <>tuioserver;
	classvar <>scserver;
	classvar <>control;
	classvar <>objects;
	classvar <>view;
	classvar <>buffer0, <>buffer1, <>buffer2, <>buffer3, <>buffer4, <>buffer5;
	classvar <>buffer6, <>buffer7, <>buffer8, <>buffer9, <>buffer10, <>buffer11;
	classvar <>buffer12, <>buffer13, <>buffer14, <>buffer15, <>buffer16, <>buffer17;
	classvar <>buffer18, <>buffer19, <>buffer20, <>buffer21, <>buffer22, <>buffer23;
	classvar <>buffer24, <>buffer25, <>buffer26, <>buffer27, <>buffer28, <>buffer29;
	classvar <>buffer30, <>buffer31, <>buffer32, <>buffer33, <>buffer34, <>buffer35;
	classvar <>pathsamples, <>buffers;
	classvar <>path, pathfile;
	classvar <>mode; // 0 => Normal; 1 => Panning
	classvar <>file;
	classvar <>source, <>effect, <>panning;
	classvar minDist = 0.15;


	*new {arg mod = 1;
        ^super.new.init (mod);
    }

	init {arg mod;
		mode = mod;
		tuioserver = SETO_OSCServer('2Dobj', nil, SETODictObj).start;
		view = XY4View.new;
		control = XY4Controller.new;
		objects = IdentityDictionary.new;
		path = "/Users/anna/Sounds/russolo-mono";
		pathsamples = List.new;

		// explosions
		pathsamples.add("explosions/61015__kayyy__splash.wav");
		pathsamples.add("explosions/84347__sandyrb__subsonic-rumble.wav");
		pathsamples.add("explosions/99960__cgeffex__large-crash.wav");
		pathsamples.add("explosions/102734__sarge4267__explosion4.wav");
		pathsamples.add("explosions/121946__hantorio__thunder-close-boem.wav");
		pathsamples.add("explosions/150139__gregsmedia__gun-shot-sound-02.wav");

		// percussion
		pathsamples.add("percussion/449__tictacshutup__prac-tom.wav");
		pathsamples.add("percussion/144522__garogourou__batonres.wav");
		pathsamples.add("percussion/21789__splashzooka__rocks.wav");
		pathsamples.add("percussion/150098__peter-lustig__teller-01.wav");
		pathsamples.add("percussion/179854__toiletrolltube__falling-metal-1-20-3-11.wav");
		pathsamples.add("percussion/185818__lloydevans09__cymbal-impact.wav");

		// screech
		pathsamples.add("screech/64383__corsica-s__spoon-bowl2.wav");
		pathsamples.add("screech/67267__robinhood76__00736-rustling-plastic-1.wav");
		pathsamples.add("screech/118340__julien-matthey__jm-noiz-buzz-01-neon-light.wav");
		pathsamples.add("screech/125388__thegoose09__monster-screech.wav");
		pathsamples.add("screech/181868__cognito-perceptu__train-screech.wav");
		pathsamples.add("screech/4211__dobroide__fire-crackling.wav");

		// voices
		pathsamples.add("voices/15362__djgriffin__tibetan-chant-1.wav");
		pathsamples.add("voices/26729__leady__insane-laughter-man-reverb.wav");
		pathsamples.add("voices/42847__freqman__psycho-scream-1.wav");
		pathsamples.add("voices/103592__robinhood76__01948-kid-laughter.wav");
		pathsamples.add("voices/146965__zabuhailo__cathowling2.wav");
		pathsamples.add("voices/171758__adam-n__groan-male-normalised.wav");

		// whispers
		pathsamples.add("whispers/2878__halleck__wind-gurgle.wav");
		pathsamples.add("whispers/90742__dangasior__catmathilda01-18dbfs.wav");
		pathsamples.add("whispers/149120__vosvoy__stomach-gurgling-severalsamples.wav");
		pathsamples.add("whispers/165617__erdie__whisper.wav");
		pathsamples.add("whispers/171550__klankbeeld__audience-becomes-still-01.wav");
		pathsamples.add("whispers/181183__hephaestus__whisper-of-wind.wav");

		// whistles
		pathsamples.add("whistles/66248__robinhood76__00773-leaking-gas-1.wav");
		pathsamples.add("whistles/86045__nextmaking__whistle-of-boat.wav");
		pathsamples.add("whistles/109354__soundcollectah__short-whistle-it-tunnel.wav");
		pathsamples.add("whistles/130289__dj-chronos__pipe-hiss-003.wav");
		pathsamples.add("whistles/164623__adam-n__air-escaping.wav");
		pathsamples.add("whistles/183915__bigfriendlyjiant__snort-2-long.wav");

		pathfile = "/Users/anna/Logs/";

		buffers = IdentityDictionary.new;

		this.initSCServer;

		CmdPeriod.add({this.cmdPeriod});

	}

	initSCServer{
		scserver = Server.local;
		scserver.boot;

		scserver.doWhenBooted {
			source = Group.head(scserver);
			effect = Group.after(source);
			panning = Group.tail(scserver);
			pathsamples.do({|item, i|
				buffers.add (i -> Buffer.read(scserver, path +/+ item));
			});
			this.initFile; // it generates a logfile
		}
	}

	initFile {
		var date = Date.getDate;
		file = File("%XY4 % \n".postf(pathfile,date.stamp), "w");
		file.write ("Date: % \n".postf(date.stamp));
		file.write("Mode: % \n".postf(mode));
	}

	stop {
		view.clear;
		tuioserver.stop;
		scserver.quit;
	}

	cmdPeriod {
		file.close;
		this.stop;
		currentEnvironment.clear;

	}

	*print {|obj|
		obj.postln;
	}

	*manageConnections {
		objects.do{|i|
			objects.do{|j|
				var dist = Point(i.pos[0],i.pos[1]).dist(Point(j.pos[0],j.pos[1]));
				if(dist<minDist){
					if(i.isKindOf(XY4FX) && j.isKindOf(XY4Player)){
						i.changeBus(j.classID); // change view
					};

					if(i.isKindOf(XY4Player) && j.isKindOf(XY4FX)){
						j.changeBus(i.classID); // change view
					};
				}
			}
		}
	}

}