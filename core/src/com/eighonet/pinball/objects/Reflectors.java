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

public class Reflectors extends TexturePacker {

	public Reflectors(World world) {
		super(world);
		// TODO Auto-generated constructor stub
	}

	private Body reflectorFModel, reflectorSModel, reflectorTModel;
	private Vector2 reflectorFModelOrigin, reflectorSModelOrigin, reflectorTModelOrigin;
	
	private static final float WIDTH = (float)1;
	
	private Texture reflectorTexture;
	
	private Sprite reflectorSprite, reflectorSprite1, reflectorSprite2, reflectorSprite3;
	
	@Override
	public void Create() {
		// 0. Create a loader for the file saved from the editor.
				FileHandle file = Gdx.files.internal("data/border.json");
				BodyEditorLoader loader = new BodyEditorLoader(file);
					
				// 1. Create a BodyDef, as usual.
				BodyDef bd1 = new BodyDef(), bd2 = new BodyDef(), bd3 = new BodyDef();
				
				bd1.type = BodyType.KinematicBody;
				bd1.position.set((float) 0.4, (float)2.6 );
				
				bd2.type = BodyType.KinematicBody;
				bd2.position.set((float) -0.45, (float)1.15 );
				
				bd3.type = BodyType.KinematicBody;
				bd3.position.set((float) -1.35, (float)2.6);
				
				// 2. Create a FixtureDef, as usual.
				FixtureDef fd = new FixtureDef();
				fd.density = -10;
				fd.friction = 0.5f;
				fd.restitution = 2f;

			
				// 3. Create a Body, as usual.
				reflectorFModel = world.createBody(bd1);
				reflectorSModel = world.createBody(bd2);
				reflectorTModel = world.createBody(bd3);
				
				
				
				reflectorFModel.setUserData("mainReflector"); 
				reflectorSModel.setUserData("mainReflector"); 
				reflectorTModel.setUserData("mainReflector");
				
				// 4. Create the body fixture automatically by using the loader.
				loader.attachFixture(reflectorFModel, "hitbox1", fd, (float) WIDTH);
				reflectorFModelOrigin = loader.getOrigin("hitbox1", WIDTH).cpy();
				
				loader.attachFixture(reflectorSModel, "hitbox1", fd, (float) WIDTH);
				reflectorSModelOrigin = loader.getOrigin("hitbox1", WIDTH).cpy();
				
				loader.attachFixture(reflectorTModel, "hitbox1", fd, (float) WIDTH);
				reflectorTModelOrigin = loader.getOrigin("hitbox1", WIDTH).cpy();
				
				reflectorSprite1 = Pack(reflectorTexture, "data/gfx/firstCB .png", WIDTH);
				reflectorSprite2 = Pack(reflectorTexture, "data/gfx/firstCB .png", WIDTH);
				reflectorSprite3 = Pack(reflectorTexture, "data/gfx/firstCB .png", WIDTH);
	}

	@Override
	public void Assembly() {
		reflectorSprite1 = Connect(reflectorSprite1, reflectorFModel, reflectorFModelOrigin);
		reflectorSprite2 = Connect(reflectorSprite2, reflectorSModel, reflectorFModelOrigin);
		reflectorSprite3 = Connect(reflectorSprite3, reflectorTModel, reflectorFModelOrigin);
	}
	
	public Sprite[] GetSprite() {
		Sprite[] spriteArray = {this.reflectorSprite1, this.reflectorSprite2, this.reflectorSprite3};
		return spriteArray;
	}

}
