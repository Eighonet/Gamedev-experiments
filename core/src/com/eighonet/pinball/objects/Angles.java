package com.eighonet.pinball.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.eighonet.pinball.BodyEditorLoader;
import com.eighonet.pinball.TexturePacker;

public class Angles extends TexturePacker {

	public Angles(World world) {
		super(world);
		// TODO Auto-generated constructor stub
	}

	private static final float WIDTH = (float) 0.8;
	
	private Vector2 angleLModelOrigin, angleRModelOrigin;
	private Body angleLModel, angleRModel;
	
	private Sprite angleLSprite;
	private Sprite angleRSprite;
	
	private Texture angleLTexture;
	private Texture angleRTexture;
	
	@Override
	public void Create() {
		// 0. Create a loader for the file saved from the editor.
		FileHandle file = Gdx.files.internal("data/border.json");
		BodyEditorLoader loader = new BodyEditorLoader(file);
		
		BodyDef bd1 = new BodyDef();
		BodyDef bd2 = new BodyDef();
		
		bd1.type = BodyType.KinematicBody;
		bd1.position.set((float) -2.7,(float)-2.9);
		bd2.type = BodyType.KinematicBody;
		bd2.position.set((float) 1,(float)-2.9);


		FixtureDef fd = new FixtureDef();
		fd.density = 1;
		fd.friction = 0.5f;
		fd.restitution = 3.5f;

	
		
		// 3. Create a Body, as usual.
		angleLModel = world.createBody(bd1);
		angleRModel = world.createBody(bd2);
		
		angleLModel.setUserData("angularReflector"); 
		angleRModel.setUserData("angularReflector"); 
		
		// 4. Create the body fixture automatically by using the loader.
		loader.attachFixture(angleLModel, "angle-left", fd, (float) WIDTH);
		loader.attachFixture(angleRModel, "angle-right", fd, (float) WIDTH);
		angleLModelOrigin = loader.getOrigin("angle-left", WIDTH).cpy();
		angleRModelOrigin = loader.getOrigin("angle-right", WIDTH).cpy();
		
		angleLSprite = Pack(angleLTexture, "data/gfx/left-angle.png", WIDTH);
		angleRSprite = Pack(angleRTexture, "data/gfx/right-angle.png", WIDTH);
	}
	
	public void Assembly() {
		angleLSprite = Connect(angleLSprite, angleLModel, angleLModelOrigin);
		angleRSprite = Connect(angleRSprite, angleRModel,angleRModelOrigin);
	}
	
	public Sprite[] GetSprite() {
		Sprite[] spriteArray = {this.angleLSprite, this.angleRSprite};
		return spriteArray;
	}
}
