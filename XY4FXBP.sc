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

XY4FXBP : XY4FX {

	*new {|tuiobj|
        ^super.new(tuiobj).init(tuiobj);
    }

	init {|tuiobj|
			this.synthDefName ="bandpass";
	}

	*initClass {
    	StartUp.add {

			SynthDef(\bandpass, {| inBus = 256, outBus = 256, freq = 440 |
				ReplaceOut.ar(outBus, BPF.ar(In.ar(inBus), freq, 0.3));
			}).add;

        }
   	}
   	setfreq {
		var rotNorm = (rotEuler[0] / 2pi);
		var tuiofreq = 100 + (rotNorm * 10000);
		tuiofreq.postln;
		this.synth.set(\freq, tuiofreq);
	}
}