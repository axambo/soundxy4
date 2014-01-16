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

XY4Object {

	var <>id, <>classID, <>pos, <>rotAxis, <>rotEuler, <>velocity, <>acceleration;
	var <>synth, <>panner;

	*new {|tuiobj|
        ^super.new.init (tuiobj);
    }

	initXY4Object {|tuiobj|
		id = tuiobj.id;
		classID = tuiobj.classID;
		pos = tuiobj.pos;
		rotAxis = tuiobj.rotAxis;
		rotEuler = tuiobj.rotEuler;
		velocity = tuiobj.velocity;
		acceleration = tuiobj.acceleration;
	}

	removesynth {
			this.synth.set(\gate, 0);
			this.synth.free;
			this.panner.free;
			"Removed:".postln;
			this.synth.postln;
	}

}