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

XY4FX : XY4Object {
	classvar <>fxClasses;
	var <>synthDefName, <>busNum = 0; // it affects all at the beginning until something gets connected

	*new {|tuiobj|
		^super.new.initXY4Object(tuiobj);
    }

	playsynth{
		this.synth = Synth(this.synthDefName, target: XY4.effect);
	}

    changeBus{|num|
			this.busNum = num;
			this.synth.set(\inBus,num);
			this.synth.set(\outBus,num);
	}

	init {|tuiobj|

	}

	*initClass {
    	StartUp.add {

		fxClasses = [XY4FXAM,XY4FXBP,XY4FXCombC,XY4FXHP,XY4FXLP,XY4FXRate];

        }
    }
}