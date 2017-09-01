job('promotion-job'){
	properties{
		promotions{
			promotion {
                name('dev')
                icon('star-gold')
                conditions {
                    manual('developer')
                }
                actions {
                    shell('echo hallo;')
                }
            }
		}
	}
}
