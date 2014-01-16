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


XY4Controller {

	var id, classID, pos, rotAxis, rotEuler, velocity, acceleration;
	var <>synth;
	var maxidplys, minidfxs;


	*new {
        ^super.new.init ();
    }

	init {
		maxidplys = 36;
		minidfxs = 107;
		132.do({|item|
			SETODictObj.setAction(item,
				{|me|
					if(XY4.objects[me.classID].isNil, // new objects
						{
							XY4.file.write("% set % % % % %\n".postf(me.tStamp, me.classID, me.id, me.pos[0], me.pos[1], me.rotEuler[0]));
							if(me.classID < maxidplys, {
								XY4.objects.add(me.classID -> XY4Player.new(me));
								if (XY4.pathsamples[me.classID].notNil,
								{
									XY4.objects[me.classID].playsynth();
								});
							});
							if(me.classID > minidfxs, {
								var fx;
								switch (me.classID % 6,
    										0, { fx = XY4FXAM.new(me)},
   											1, { fx = XY4FXBP.new(me)},
    										2, { fx = XY4FXCombC.new(me)},
    										3, { fx = XY4FXHP.new(me)},
    										4, { fx = XY4FXLP.new(me)},
   	 										5, { fx = XY4FXRate.new(me)}
										);
								XY4.objects.add(me.classID -> fx);
								fx.playsynth;
							});

						},{ // update objects
							XY4.file.write("% update % % % % %\n".postf(me.tStamp, me.classID, me.id, me.pos[0], me.pos[1], me.rotEuler[0]));
							if(me.classID < maxidplys, {
								XY4.objects[me.classID].setvolume();
								XY4.objects[me.classID].setpan4();
							});
							if(me.classID > maxidplys, {
								if (XY4.objects[me.classID].isKindOf(XY4FXHP),
									{
										XY4.objects[me.classID].setfreq()
								});
								if (XY4.objects[me.classID].isKindOf(XY4FXLP),
									{
										XY4.objects[me.classID].setfreq()
								});
								if (XY4.objects[me.classID].isKindOf(XY4FXRate),
									{
										XY4.objects[me.classID].setps()
								});
								if (XY4.objects[me.classID].isKindOf(XY4FXBP),
									{
										XY4.objects[me.classID].setfreq()
								});
								if (XY4.objects[me.classID].isKindOf(XY4FXAM),
									{
										XY4.objects[me.classID].setfreq()
								});
								if (XY4.objects[me.classID].isKindOf(XY4FXCombC),
									{
										XY4.objects[me.classID].setfreq()
								});
							});
							XY4.manageConnections();
						}
					);

				},
				{|me| // delete objects
					XY4.file.write("% del % % % % %\n".postf(me.tStamp, me.classID, me.id, me.pos[0], me.pos[1], me.rotEuler[0]));
					XY4.objects[me.classID].removesynth();
					XY4.objects.removeAt(me.classID);
				}
			);
		});

	}


	print {|tuiobj|

		id = tuiobj.id;
		classID = tuiobj.classID;
		pos = tuiobj.pos;
		rotAxis = tuiobj.rotAxis;
		rotEuler = tuiobj.rotEuler;
		velocity = tuiobj.velocity;
		acceleration = tuiobj.acceleration;

		[id, classID, pos, rotAxis, rotEuler, velocity, acceleration].postln;

	}

}