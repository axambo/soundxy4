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

XY4View {
	var <>window;
	var <view;
	var <viewobj;
	//var viewtestareatable;

	*new {
        ^super.new.init
    }

	init {
		window = Window("XY4", Window.screenBounds).front;
		view = UserView(window, window.view.bounds).background_(Color.blue);
		viewobj = UserView(window, window.view.bounds);
		//viewtestareatable = UserView(window, Rect(262, 3, 740, 729)).background_(Color.red);

		viewobj.drawFunc = {|me|
			var strokeColor = Color.gray(0.0);
			var fillColor = [Color.red, Color.yellow, Color.new255(255, 165, 0), Color.new255(255, 0, 255), Color.new255(211, 211, 211), Color.green, Color.black];
			var extent = 100;
			var xCmin = 0.22;
			var xCmax = 0.84;
			var intxC = xCmax - xCmin;
			var yCmin = 0.02;
			var yCmax = 0.86;
			var intyC = yCmax - yCmin;
			var xPoffset = 276;
			var yPoffset = 20;
			var tablewP = 660;
			var tablehP = 685;
			var numcat = 6;

			XY4.objects.do{|obj|

				var x, y, xNor, yNor, numsound;
				xNor = (obj.pos[0] - xCmin) / intxC;
				x = (xNor * tablewP) + xPoffset;
				yNor = (obj.pos[1] - yCmin) / intyC;
				y = (yNor * tablehP) + yPoffset;
				numsound = (obj.classID % numcat) + 1;

				Pen.use{
						Pen.strokeColor = strokeColor;
						Pen.fillColor = fillColor[obj.classID/numcat];

						if(obj.isKindOf(XY4FX), {
							Pen.fillColor = fillColor[obj.busNum/numcat]
						});

						Pen.translate(x + (-0.5 * extent), y + (-0.5 * extent));
						Pen.rotate(obj.rotEuler[0], extent * 0.5, extent * 0.5);
						Pen.addRect(Rect(0, 0, extent, extent));
						Pen.line((0.5 * extent)@(0.5 * extent), (0.5 * extent)@(extent * 1.2));
						Pen.moveTo(extent@extent);
						numsound.do{|item| Pen.addRect(Rect(extent * 1.1 - (item * 10) - (extent/2), extent *1.1, extent/10, extent/10));};
						Pen.fillStroke;

						if(obj.isKindOf(XY4Player), {
							Pen.color = fillColor[obj.classID/numcat];
							Pen.stringAtPoint("%".format(((((obj.rotEuler[0]/2pi)/(-1))+1)*100).asInteger), (extent * 1)@(extent * 1));
						});

						if(obj.isKindOf(XY4FX), {
							Pen.color = fillColor[obj.busNum/numcat];
							Pen.stringAtPoint("%".format(((((obj.rotEuler[0]/2pi)/(-1))+1)*100).asInteger), (extent * 1)@(extent * 1));
						});

					}

			}
		};
		viewobj.animate = true;
	}


	clear{
		window.close;
	}


}