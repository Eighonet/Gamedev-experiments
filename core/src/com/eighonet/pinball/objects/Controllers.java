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

public class Controllers extends TexturePacker {

	public Controllers(World world) {
		super(world);
		// TODO Auto-generated constructor stub
	}

	private Body controllerLModel, controllerRModel;
	
	
	private Vector2 controllerLModelOrigin, controllerRModelOrigin;


	private static final float WIDTH = (float) 1;
	
	private Vector2 angleLModelOrigin, angleRModelOrigin;
	private Body angleLModel, angleRModel;
	
	private Sprite angleLSprite;
	private Sprite angleRSprite;
	
	private Texture angleLTexture;
	private Texture angleRTexture;
	
	@Override
	public void Create() {
		FileHandle file = Gdx.files.internal("data/border.json");
		BodyEditorLoader loader = new BodyEditorLoader(file);
		
		BodyDef bdL = new BodyDef();
		bdL.type = BodyType.KinematicBody;
		bdL.position.set((float)-1.7,(float) -4);
	
		FixtureDef fd = new FixtureDef();
		fd.density = (float)0.1;
		fd.friction = 0.5f;
		fd.restitution = 0.01f;

		controllerLModel = world.createBody(bdL);
		controllerRModel = world.createBody(bdL);
		
		controllerLModel.setUserData("leftController"); 
		controllerRModel.setUserData("rightController"); 
	
		controllerLModel.setTransform((float)-1.7,(float) -4, -(float)0.8636);
		controllerRModel.setTransform((float)0.9,(float) -3.8, (float)3.14);
		
		loader.attachFixture(controllerLModel, "leftControllerDown", fd, (float)  WIDTH );
		controllerLModelOrigin = loader.getOrigin("leftControllerDown", WIDTH).cpy();
		
		loader.attachFixture(controllerRModel, "leftControllerDown", fd, (float) WIDTH);
		controllerRModelOrigin = loader.getOrigin("leftControllerDown", WIDTH).cpy();
	}
	
	public void Assembly() {
		angleLSprite = Connect(angleLSprite, angleLModel, angleLModelOrigin);
		angleRSprite = Connect(angleRSprite, angleRModel,angleRModelOrigin);
	}
	
	public Sprite[] GetSprite() {
		Sprite[] spriteArray = {this.angleLSprite, this.angleRSprite};
		return spriteArray;
}
	
	public Body[] GetModels() {
		Body[] bodyArray = {this.controllerLModel, this.controllerRModel};
		return bodyArray;
}
}
