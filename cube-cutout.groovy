import eu.mihosoft.vrl.v3d.svg.*;

import com.neuronrobotics.bowlerstudio.scripting.ScriptingEngine

import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Extrude;
import eu.mihosoft.vrl.v3d.Polygon

File f = ScriptingEngine
	.fileFromGit(
		"https://github.com/esuts/Inkscape-Plugin-Demo.git",//git repo URL
		"master",//branch
		"Inkscape-Plugin-Demo.SVG"// File from within the Git repo
	)
println "Extruding SVG "+f.getAbsolutePath()
SVGLoad s = new SVGLoad(f.toURI())

CSG base = s.extrudeLayerToCSG(4, "base") 
CSG cutout = s.extrudeLayerToCSG(3,"cutout")
.move(0, 0, 2)

CSG subtraction = base.difference(cutout)

CSG fireraised = s.extrudeLayerToCSG(4, "fireraised") 
CSG firecutout = s.extrudeLayerToCSG(3,"firecut")
.move(0, 0, 2)

CSG firesubtraction = fireraised.difference(firecutout)

return [CSG.unionAll([subtraction,firesubtraction])]
