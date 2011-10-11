/*
Copyright (C) 1997-2001 Id Software, Inc.

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  

See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.

*/
/* Modifications
   Copyright 2003-2004 Bytonic Software
   Copyright 2010 Google Inc.
*/
package com.googlecode.gwtquake.shared.render;

import java.nio.ByteBuffer;
import java.util.Arrays;

import com.googlecode.gwtquake.*;
import com.googlecode.gwtquake.shared.common.Constants;
import com.googlecode.gwtquake.shared.common.TextureInfo;
import com.googlecode.gwtquake.shared.game.*;


public class ModelSurface
{

	public int visframe; // should be drawn when node is crossed

	public Plane plane;
	public int flags;

	public int firstedge; // look up in model->surfedges[], negative numbers
	public int numedges; // are backwards edges

	public short texturemins[] = { 0, 0 };
	public short extents[] = { 0, 0 };

	public int light_s, light_t; // gl lightmap coordinates
	public int dlight_s, dlight_t;
	// gl lightmap coordinates for dynamic lightmaps

	public GlPolygon polys; // multiple if warped
	public ModelSurface texturechain;
	public ModelSurface lightmapchain;

	// TODO check this
	public ModelTextureInfo texinfo = new ModelTextureInfo();

	// lighting info
	public int dlightframe;
	public int dlightbits;

	public int lightmaptexturenum;
	public byte styles[] = new byte[Constants.MAXLIGHTMAPS];
	public float cached_light[] = new float[Constants.MAXLIGHTMAPS];
	// values currently used in lightmap
	//public byte samples[]; // [numstyles*surfsize]
	public ByteBuffer samples; // [numstyles*surfsize]
	
	public void clear() {
		visframe = 0;
		plane.clear();
		flags = 0;

		firstedge = 0;
		numedges = 0;

		texturemins[0] = texturemins[1] = -1;
		extents[0] = extents[1] = 0;

		light_s = light_t = 0;
		dlight_s = dlight_t = 0;

		polys = null;
		texturechain = null;
		lightmapchain = null;

		//texinfo = new mtexinfo_t();
		texinfo.clear();

		dlightframe = 0;
		dlightbits = 0;

		lightmaptexturenum = 0;
		
		for (int i = 0; i < styles.length; i++)
		{
			styles[i] = 0;
		}
		for (int i = 0; i < cached_light.length; i++)
		{
			cached_light[i] = 0;
		}
		if (samples != null) samples.clear();
	}
}
