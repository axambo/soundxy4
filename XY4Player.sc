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

XY4Player : XY4Object {

	*new {|tuiobj|
		^super.new.initXY4Object (tuiobj);
    }

	init {|tuiobj|

	}

	*initClass {
    	StartUp.add {
        	SynthDef(\player, {| outBus = 0, bufnum = 0, gate = 1, vol = 0.5, rate = 1.0 |
				var playbuf;

				// PanB2
				playbuf = PlayBuf.ar(numChannels: 1, bufnum: bufnum, rate: BufRateScale.kr(bufnum), loop: 1);
    			Out.ar(outBus,
    				playbuf * vol;
    			)
			}).add;

			// Ambisonics synth (mode = 1), make sure to adapt it to the # of speakers
			SynthDef(\panB2, {| inBus = 0, outBus = 0, xpos = 0, ypos = 0, gain = 0.7 |
				var w, x, y, a, b, c, d, p, q;
				Out.ar(outBus,
       					#w, x, y = PanB2.ar(In.ar(inBus, 1), atan2(ypos, xpos) / pi, gain);
   						// for 4-speakers reproduction
   						/*#a, b, c, d = DecodeB2.ar(4, w, x, y);
   						/[c, b, d, a] * [1, 0.9, 0.425, 0.78];*/
   						// for stereo recording:
   						DecodeB2.ar(2, w, x, y);

				)
			}).add;


			// Flat synth (mode = 0)
			SynthDef(\pan4, {| inBus = 0, outBus = 0, xpos = 0, ypos = 0 |
				Out.ar(outBus,
					Pan4.ar(In.ar(inBus, 1), xpos, ypos) * [1, 0.9, 0.425, 0.78];
				)
			}).add;

        }
    }

	playsynth {
		"TESTING PLAYSYNTH".postln;
		this.synth = Synth(
    		"player",
   			[\outBus, classID, \bufnum, XY4.buffers[classID].bufnum],
   			XY4.source;
   		);

		if (XY4.mode == 1, {
			this.panner = Synth(
    		"panB2",
    		[\inBus, classID, \outBus, 0],
    		XY4.panning);
			},
			{
			this.panner = Synth(
    		"pan4",
    		[\inBus, classID, \outBus, 0],
    		XY4.panning);
			}
		);
	}

	setvolume {
		var rotNorm = (rotEuler[0] / 2pi);
		var invRotNorm = (rotNorm / -1) + 1;
		this.synth.set(\vol, invRotNorm);
	}

	setpan4 {
		var xNorm = (pos[0] - 0.22) / (0.84 - 0.22);
		var yNorm = (pos[1] - 0.02) / (0.86 - 0.02);
		var xNormPan4 = -1 + (xNorm * 2);
		var yNormPan4 = (-1 + (yNorm * 2)) * (-1);
		var xNormCircle = (xNorm * 2) - 1;
		var yNormCircle = (-2 * yNorm) + 1;


		if (XY4.mode == 1, {
			this.panner.set(\xpos, xNormCircle);
			this.panner.set(\ypos, yNormCircle);
		});

		if (XY4.mode == 0, {
			this.panner.set(\xpos, 0);
			this.panner.set(\ypos, 0);
		});

}

}




