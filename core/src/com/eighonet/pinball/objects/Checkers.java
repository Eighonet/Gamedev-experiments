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

public class Checkers extends TexturePacker{

	public Checkers(World world) {
		super(world);
		// TODO Auto-generated constructor stub
	}
	
	private Body[] checkerModels = new Body[7];
	private Sprite[] checkerSprite = new Sprite[7];
	private Vector2[] checkerModelsOrigin = new Vector2[7];
	private Texture checkerTexture;
	protected static final float WIDTH = 0.32f, WIDTH2 = 0.52f;
	
	@Override
	public void Create() {
		FileHandle file = Gdx.files.internal("data/border.json");
		BodyEditorLoader loader = new BodyEditorLoader(file);
			
		BodyDef bd1 = new BodyDef(), bd2 = new BodyDef(), bd3 = new BodyDef(),
				bd4 = new BodyDef(), bd5 = new BodyDef(), bd6 = new BodyDef(), bd7 = new BodyDef();
		
		bd1.type = BodyType.StaticBody;
		bd1.position.set((float) -0.9, (float)5 );
		
		bd2.type = BodyType.StaticBody;
		bd2.position.set((float) -0.1, (float)5);
		
		bd3.type = BodyType.StaticBody;
		bd3.position.set((float) 0.7, (float)5);
		
		bd4.type = BodyType.StaticBody;
		bd4.position.set((float) -2.1, (float)2.8f);
		
		bd5.type = BodyType.StaticBody;
		bd5.position.set((float) -2.5, (float)-0.6f);
		
		bd6.type = BodyType.StaticBody;
		bd6.position.set((float) -2.25, (float)2.1f);
		
		bd7.type = BodyType.StaticBody;
		bd7.position.set((float) 1.6, (float)2.35f);
		
		FixtureDef fd = new FixtureDef();

		fd.isSensor = true;
	
		checkerModels[0] = world.createBody(bd2);
		checkerModels[1] = world.createBody(bd1);
		checkerModels[2] = world.createBody(bd3);
		checkerModels[3] = world.createBody(bd4);
		checkerModels[4] = world.createBody(bd5);
		checkerModels[5] = world.createBody(bd6);
		checkerModels[6] = world.createBody(bd7);
		
		checkerModels[0].setUserData("0"); 
		checkerModels[1].setUserData("1"); 
		checkerModels[2].setUserData("2");
		checkerModels[3].setUserData("3"); 
		checkerModels[4].setUserData("4");
		checkerModels[5].setUserData("5"); 
		checkerModels[6].setUserData("6"); 
		
		
		// 4. Create the body fixture automatically by using the loader.
		loader.attachFixture(checkerModels[0], "htbox2a", fd, (float) WIDTH);
		checkerModelsOrigin[0] = loader.getOrigin("htbox2a", WIDTH).cpy();
		
		loader.attachFixture(checkerModels[1], "htbox2a", fd, (float) WIDTH);
		checkerModelsOrigin[1] = loader.getOrigin("htbox2a", WIDTH).cpy();
		
		loader.attachFixture(checkerModels[2], "htbox2a", fd, (float) WIDTH);
		checkerModelsOrigin[2] = loader.getOrigin("htbox2a", WIDTH).cpy();

		loader.attachFixture(checkerModels[3], "htbox2a", fd, (float) WIDTH);
		checkerModelsOrigin[3] = loader.getOrigin("htbox2a", WIDTH).cpy();
		
		loader.attachFixture(checkerModels[4], "htbox2a", fd, (float) WIDTH2);
		checkerModelsOrigin[4] = loader.getOrigin("htbox2a", WIDTH2).cpy();
		
		loader.attachFixture(checkerModels[5], "htbox2a", fd, (float) WIDTH);
		checkerModelsOrigin[5] = loader.getOrigin("htbox2a", WIDTH).cpy();
		
		loader.attachFixture(checkerModels[6], "htbox2a", fd, (float) WIDTH);
		checkerModelsOrigin[6] = loader.getOrigin("htbox2a", WIDTH).cpy();
		
		checkerSprite[0] = Pack(checkerTexture, "data/gfx/pCP .png", WIDTH);
		checkerSprite[1] = Pack(checkerTexture, "data/gfx/pCP .png",WIDTH);
		checkerSprite[2] = Pack(checkerTexture, "data/gfx/pCP .png", WIDTH);
		checkerSprite[3] = Pack(checkerTexture, "data/gfx/pCP .png", WIDTH);
		checkerSprite[4] = Pack(checkerTexture, "data/gfx/pCP .png", WIDTH2);
		checkerSprite[5] = Pack(checkerTexture, "data/gfx/pCP .png", WIDTH);
		checkerSprite[6] = Pack(checkerTexture, "data/gfx/pCP .png", WIDTH);
	}

	@Override
	public void Assembly() {
		checkerSprite[0] = Connect(checkerSprite[0], checkerModels[0], checkerModelsOrigin[0]);
		checkerSprite[1] =  Connect(checkerSprite[1], checkerModels[1], checkerModelsOrigin[1]);
		checkerSprite[2] =  Connect(checkerSprite[2], checkerModels[2], checkerModelsOrigin[2]);
		checkerSprite[3] =  Connect(checkerSprite[3], checkerModels[3], checkerModelsOrigin[3]);
		checkerSprite[4] =  Connect(checkerSprite[4], checkerModels[4], checkerModelsOrigin[4]);
		checkerSprite[5] =  Connect(checkerSprite[5], checkerModels[5], checkerModelsOrigin[5]);
		checkerSprite[6] =  Connect(checkerSprite[6], checkerModels[6], checkerModelsOrigin[6]);
		
	}

	@Override
	public Sprite[] GetSprite() {
		return checkerSprite;
	}

	public Body[] GetModels() {
		return this.checkerModels;
	}

}
